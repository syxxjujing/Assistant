package com.jujing.assistant.backend.plugins

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ListPopupWindow
import android.widget.Toast
import com.jujing.assistant.R
import com.jujing.assistant.backend.WechatPackage
import com.jujing.assistant.backend.WechatPackage.SnsUploadUI
import com.jujing.assistant.backend.storage.cache.SnsCache
import com.jujing.assistant.frontend.wechat.ListPopupWindowPosition
import com.jujing.assistant.frontend.wechat.StringListAdapter
import com.jujing.assistant.util.*
import com.jujing.assistant.util.ViewUtil.getListViewFromSnsActivity
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import com.jujing.assistant.util.ViewUtil.getViewAtPosition
import com.jujing.assistant.util.ViewUtil.dp2px
import com.jujing.myrobot.C
import com.jujing.myrobot.util.MessageUtil
import de.robv.android.xposed.XposedBridge
import org.bouncycastle.util.Strings
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

object SnsForward{
    private val pkg = WechatPackage

    private const val ROOT_TAG = "TimelineObject"
    private const val ID_TAG   = ".TimelineObject.id"
    fun hookSnsTimeLineUI(){
        XposedHelpers.findAndHookMethod(pkg.SnsUploadUI, "onCreate", C.Bundle, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {//TODO 微信6.6.7的得这样做!!!!!
                val activity = param.thisObject as Activity?
                val intent = (param.thisObject as Activity).intent ?: return
//                if (intent.getBooleanExtra("Ksnsforward", false)) {
                LogTool.d("来填写朋友圈的内容了!!!!!-----")
                val content = intent.getStringExtra("Kdescription")
                val editTextField = pkg.SnsUploadUIEditTextField
                val editText = XposedHelpers.getObjectField(param.thisObject, editTextField)
                XposedHelpers.callMethod(editText, "setText", content)
            }
        })


        XposedHelpers.findAndHookMethod(pkg.SnsTimeLineUI,"onStart",object :XC_MethodHook(){
            override fun beforeHookedMethod(param: MethodHookParam) {
                super.beforeHookedMethod(param)
                val activity = param.thisObject as? Activity ?: return

                // Hook SnsTimeLineUI to popup a menu during long click.
                val listView = getListViewFromSnsActivity(activity) ?: return
                // Set onTouchListener for the list view.
                var lastKnownX = 0
                var lastKnownY = 0
                val detector = GestureDetector(listView.context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onLongPress(e: MotionEvent?) {
                        val position = listView.pointToPosition(lastKnownX, lastKnownY)
//                        listView.getviewat
                        val view = listView.getViewAtPosition(position)
                        val item = listView.getItemAtPosition(position)
                        val snsId = XposedHelpers.getLongField(item, "field_snsId")
                        val popup = ListPopupWindowPosition(listView, lastKnownX, lastKnownY)
                        onTimelineItemLongClick(listView, view, snsId, popup)
                    }
                })
                (listView as View).setOnTouchListener { _, event ->
                    lastKnownX = event.x.toInt()
                    lastKnownY = event.y.toInt()
                    return@setOnTouchListener detector.onTouchEvent(event)
                }
            }

        })



        XposedBridge.hookAllConstructors(pkg.ImgInfoStorage, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                if (pkg.ImgStorageObject !== param.thisObject) {
                    pkg.ImgStorageObject = param.thisObject
                }
            }
        })



        PackageUtil.findAndHookMethod(pkg.XMLParserClass, pkg.XMLParseMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val xml = param.args[0] as String?
                val root = param.args[1] as String?
                val result = param.result as MutableMap<String, String>? ?: return
                tryAsynchronously {
                    if (root == ROOT_TAG) {
                        val id = result[ID_TAG]
                        LogTool.e("id 92---->$id-------->$result")
                        if (id != null) {
                            SnsCache[id] = SnsCache.SnsInfo(result)
                        }
                    }
                }


            }
        })
    }
    fun tryAsynchronously(func: () -> Unit): Thread {
        return thread(start = true) { func() }.apply {
            setUncaughtExceptionHandler { _, t ->
                Log.e("Xposed", Log.getStackTraceString(t))
            }
        }
    }


    // ForwardAsyncTask is the AsyncTask that downloads SNS contents and invoke SnsUploadUI.
    class ForwardAsyncTask(snsId: Long, context: Context) : AsyncTask<Void, Void, Throwable?>() {

        private val snsId = MessageUtil.longToDecimalString(snsId)
        private val snsInfo = SnsCache[this.snsId]
        private val context = WeakReference(context)
        private val storage = Environment.getExternalStorageDirectory().absolutePath + "/WechatMagician"

        override fun doInBackground(vararg params: Void): Throwable? {
            return try {
                val state = Environment.getExternalStorageState()
                if (state != Environment.MEDIA_MOUNTED) {
                    throw Error("SD card is not presented! (state: $state)")
                }
                if (snsInfo == null) {
                    val prompt = "数据失效或已删除"
                    throw Error("$prompt (snsId: $snsId)")
                }


                if (snsInfo.contentUrl != null) {
                    if (snsInfo.medias.isNotEmpty()) {
                        val media = snsInfo.medias[0]
                        DownloadUtil.downloadThumb("$storage/.cache/0.thumb", media.thumb)
                    }
                    return null
                }
                snsInfo.medias.mapIndexed { i, media ->
                    tryAsynchronously {
                        when (media.type) {
                            "2" -> DownloadUtil.downloadImage("$storage/.cache/$i", media)
                            "6" -> DownloadUtil.downloadVideo("$storage/.cache/$i", media)
                        }
                    }
                }.forEach(Thread::join); null
            } catch (t: Throwable) { t }
        }

        override fun onPostExecute(result: Throwable?) {
            if (result != null) {
                val message = result.localizedMessage
                Toast.makeText(context.get(), message, Toast.LENGTH_LONG).show()
                return
            }
            LogTool.e("alkdklansdklnalkn--->${snsInfo?.content}\n" +
                    "title----->${snsInfo?.title}---\n" +
                    "contentUrl----->${snsInfo?.contentUrl}-----\n" +
                    "medias ----->${snsInfo?.medias}")
            val intent = Intent(context.get(), SnsUploadUI)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra("Ksnsforward", true)
                    .putExtra("Ksnsupload_type", 9)
                    .putExtra("Kdescription", snsInfo?.content)


            when {
                snsInfo?.contentUrl != null -> {
                    val buffer = FileUtil.readBytesFromDisk("$storage/.cache/0.thumb")
                    intent.putExtra("Ksnsupload_type", 1)
                            .putExtra("Ksnsupload_title", snsInfo.title)
                            .putExtra("Ksnsupload_link", snsInfo.contentUrl)
                            .putExtra("Ksnsupload_imgbuf", buffer)
                }
                snsInfo?.medias?.isEmpty() == false -> {
                    when (snsInfo.medias[0].type) {
                        "2" -> {
                            intent.putStringArrayListExtra(
                                    "sns_kemdia_path_list",
                                    ArrayList((0 until snsInfo.medias.size).map {
                                        "$storage/.cache/$it"
                                    })
                            )
                            intent.removeExtra("Ksnsupload_type")
                        }
                        "6" -> {
                            intent.putExtra("Ksnsupload_type", 14)
                                    .putExtra("sight_md5", snsInfo.medias[0].main?.md5)
                                    .putExtra("KSightPath", "$storage/.cache/0")
                                    .putExtra("KSightThumbPath", "$storage/.cache/0.thumb")
                        }
                    }
                }
            }
            context.get()?.startActivity(intent)
        }
    }


    // Show a popup menu in SnsTimelineUI
    private fun onTimelineItemLongClick(parent: AdapterView<*>, view: View, snsId: Long, position: ListPopupWindowPosition?): Boolean {
        val textForward = "转发"
        val textScreenshot = "截图"
        ListPopupWindow(parent.context).apply {
            if (position != null) {
                // Calculate list view size
                val location = IntArray(2)
                position.anchor.getLocationOnScreen(location)
                val bottom = location[1] + position.anchor.height

                // Set position for popup window
                anchorView = position.anchor
                horizontalOffset = position.x - position.anchor.left
                verticalOffset = position.y - bottom
            } else {
                anchorView = view
            }

            // Set general properties for popup window
            width = parent.context.dp2px(120)
            setDropDownGravity(Gravity.CENTER)
            setAdapter(StringListAdapter(view.context, listOf(textForward, textScreenshot)))
            setOnItemClickListener { _, _, operation, _ ->
                onTimelineItemPopupMenuSelected(view, snsId, operation)
                dismiss()
            }
        }.show()
        return true
    }

    // Handle the logic about the popup menu in SnsTimelineUI
    private fun onTimelineItemPopupMenuSelected(itemView: View, snsId: Long, operation: Int): Boolean {
        val promptWait = "请稍候片刻……"
        val promptScreenshot = "截图已保存至"
        when (operation) {
            0 -> { // Forward
                ForwardAsyncTask(snsId, itemView.context).execute()
                Toast.makeText(itemView.context, promptWait, Toast.LENGTH_SHORT).show()
                return true
            }
            1 -> { // Screenshot
                val context = itemView.context ?: return true
                try {
                    val path = ImageUtil.createScreenshotPath()
                    val bitmap = ViewUtil.drawView(itemView)
                    FileUtil.writeBitmapToDisk(path, bitmap)
                    FileUtil.notifyNewMediaFile(path, itemView.context)
                    Toast.makeText(context, "$promptScreenshot $path", Toast.LENGTH_SHORT).show()
                } catch (t: Throwable) {
                    Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return false
    }
}