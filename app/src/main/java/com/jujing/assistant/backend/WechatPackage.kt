package com.jujing.assistant.backend

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.BaseAdapter
import com.jujing.assistant.BuildConfig
import com.jujing.assistant.Global.tryOrNull
import com.jujing.assistant.Global.tryWithThread
import com.jujing.assistant.util.LogTool
import com.jujing.assistant.util.PackageUtil
import com.jujing.assistant.util.PackageUtil.findClassIfExists
import com.jujing.assistant.util.PackageUtil.findClassesFromPackage
import com.jujing.assistant.util.PackageUtil.findFieldsWithGenericType
import com.jujing.assistant.util.PackageUtil.findFieldsWithType
import com.jujing.myrobot.C
import com.jujing.myrobot.util.Version
import com.jujing.myrobot.util.WaitChannel
import de.robv.android.xposed.XposedHelpers.*
import de.robv.android.xposed.callbacks.XC_LoadPackage
import net.dongliu.apk.parser.ApkFile
import java.lang.ref.WeakReference
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write
/**
 * 如有疑问，请加QQ群：536941874
 *
 * 作者微信：laibujile001
 *
 * */
// WechatPackage analyzes and stores critical classes and objects in Wechat application.
// These classes and objects will be used for hooking and tampering with runtime data.
object WechatPackage {

    // initializeChannel resumes all the thread waiting for the WechatPackage initialization.
    private val initializeChannel = WaitChannel()

    // status stores the working status of all the hooks.
    private val statusLock = ReentrantReadWriteLock()
    private val status: HashMap<String, Boolean> = hashMapOf()

    // These stores necessary information to match signatures.
    @Volatile
    var packageName: String = ""
    @Volatile
    var loader: ClassLoader? = null
    @Volatile
    var version: Version? = null
    @Volatile
    var classes: List<String>? = null

    @Volatile
    var density: Float? = null //屏幕的密度

    // These are the cache of important global objects
    @Volatile
    var AddressAdapterObject: WeakReference<BaseAdapter?> = WeakReference(null)
    @Volatile
    var ConversationAdapterObject: WeakReference<BaseAdapter?> = WeakReference(null)
    @Volatile
    var MsgStorageObject: Any? = null
    @Volatile
    var ImgStorageObject: Any? = null

    private fun <T> innerLazy(name: String, initializer: () -> T?): Lazy<T> = lazy {
        initializeChannel.wait()
        initializer() ?: throw Error("Failed to evaluate $name")
    }

    private val WECHAT_PACKAGE_SQLITE: String by innerLazy("WECHAT_PACKAGE_SQLITE") {
        when {
            version!! >= Version("6.5.8") -> "com.tencent.wcdb"
            else -> "com.tencent.mmdb"
        }
    }
    private val WECHAT_PACKAGE_UI: String         by lazy { "${packageName}.ui" }
    private val WECHAT_PACKAGE_SNS_UI: String     by lazy { "${packageName}.plugin.sns.ui" }
    private val WECHAT_PACKAGE_GALLERY_UI: String by lazy { "${packageName}.plugin.gallery.ui" }


    val ChatroomInfoUI: Class<*> by innerLazy("ChatroomInfoUI") {
        findClassIfExists("com.tencent.mm.plugin.chatroom.ui.ChatroomInfoUI", loader)
    }

    val SingleChatInfoUI: Class<*> by innerLazy("SingleChatInfoUI") {
        findClassIfExists("com.tencent.mm.ui.SingleChatInfoUI", loader)

    }

    val SelfQRCodeUI: Class<*> by innerLazy("com.tencent.mm.plugin.setting.ui.setting.SelfQRCodeUI") {
        findClassIfExists("com.tencent.mm.plugin.setting.ui.setting.SelfQRCodeUI", loader)
    }


    val SelectContactUI: Class<*> by innerLazy("com.tencent.mm.ui.contact.SelectContactUI") {
        findClassIfExists("com.tencent.mm.ui.contact.SelectContactUI", loader)

    }

    val ModRemarkRoomNameUI: Class<*> by innerLazy("ModRemarkRoomNameUI") {
        findClassIfExists("com.tencent.mm.plugin.chatroom.ui.ModRemarkRoomNameUI", loader)
    }

    //    val banClazz0:Class<*> by innerLazy("banClazz0"){
//        findClassIfExists(FitNameUtil.banClazz0, loader)
//    }
//
//
//    val banClazz1:Class<*> by innerLazy("banClazz1"){
//        findClassIfExists(FitNameUtil.banClazz1, loader)
//    }
    val XMLParserClass: Class<*> by innerLazy("XMLParserClass") {
        findClassesFromPackage(loader!!, classes!!, "${packageName}.sdk.platformtools")
                .filterByMethod(C.Map, C.String, C.String)
                .firstOrNull()
    }
    val XMLParseMethod: Method by innerLazy("XMLParseMethod") {
        findMethodsByExactParameters(
                XMLParserClass, C.Map, C.String, C.String
        ).firstOrNull()
    }


//    val ImgInfoStorage: Class<*> by wxLazy("ImgInfoStorage") {
//        findClassesFromPackage(wxLoader!!, wxClasses!!, wxPackageName, 1)
//                .filterByMethod(C.String, C.String, C.String, C.String, C.Boolean)
//                .firstOrNull()
//    }
//    val ImgStorageClass: Class<*> by innerLazy("ImgStorageClass") {
//        findClassesFromPackage(loader!!, classes!!, packageName, 1)
//                .filterByMethod(C.String, "a", C.String, C.String, C.String, C.Boolean)
//                .firstOrNull()
//    }
    val SQLiteDatabase: Class<*> by innerLazy("SQLiteDatabase") {
        findClassIfExists("${WECHAT_PACKAGE_SQLITE}.database.SQLiteDatabase", loader)
    }

    val SnsActivity: Class<*> by innerLazy("SnsActivity") {
        findClassesFromPackage(loader!!, classes!!, WECHAT_PACKAGE_SNS_UI)
                .filterByField("${WECHAT_PACKAGE_UI}.base.MMPullDownView")
                .firstOrNull()
    }

    val EncEngine_transFor: Method by innerLazy("EncEngine_transFor") {
        findMethodsByExactParameters(EncEngine, C.Int, C.ByteArray, C.Int).firstOrNull()
    }
    val SnsUploadUIEditTextField: String by innerLazy("SnsUploadUIEditTextField") {
        findFieldsWithType(
                SnsUploadUI, "${WECHAT_PACKAGE_SNS_UI}.SnsEditText"
        ).firstOrNull()?.name ?: ""
    }
    val EncEngine: Class<*> by innerLazy("EncEngine") {
        findClassesFromPackage(loader!!, classes!!, "${packageName}.modelsfs")
                .filterByMethod(null, "seek", C.Long)
                .filterByMethod(null, "free")
                .firstOrNull()
    }
    val ImgInfoStorage_mBitmapCache: Field by innerLazy("ImgInfoStorage_mBitmapCache") {
        findFieldsWithGenericType(
                ImgInfoStorage, "${LruCacheWithListener.canonicalName}<java.lang.String, android.graphics.Bitmap>")
                .firstOrNull()?.apply { isAccessible = true }
    }
    val SnsUploadUI: Class<*> by innerLazy("SnsUploadUI") {
        findClassesFromPackage(loader!!, classes!!, WECHAT_PACKAGE_SNS_UI)
                .filterByField("${WECHAT_PACKAGE_SNS_UI}.LocationWidget")
                .filterByField("${WECHAT_PACKAGE_SNS_UI}.SnsUploadSayFooter")
                .firstOrNull()

//        findClassIfExists("com.tencent.mm.plugin.sns.ui.SnsUploadUI", loader)
    }
    val ImgInfoStorage: Class<*> by innerLazy("ImgInfoStorage") {
        findClassesFromPackage(loader!!, classes!!, packageName, 1)
                .filterByMethod(C.String, C.String, C.String, C.String, C.Boolean)
                .firstOrNull()
    }
    val LruCache: Class<*> by innerLazy("LruCache") {
        findClassesFromPackage(loader!!, classes!!, "$packageName.sdk.platformtools")
                .filterByMethod(null, "trimToSize", C.Int)
                .firstOrNull()
    }
    val LruCacheWithListener: Class<*> by innerLazy("LruCacheWithListener") {
        findClassesFromPackage(loader!!, classes!!, packageName, 1)
                .filterBySuper(LruCache)
                .firstOrNull()
    }
    val ImgInfoStorage_load: Method by innerLazy("ImgInfoStorage_load") {
        findMethodsByExactParameters(ImgInfoStorage, C.String, C.String, C.String, C.String, C.Boolean)
                .firstOrNull()?.apply { isAccessible = true }
    }

    val LruCacheWithListener_put: Method by innerLazy("LruCacheWithListener_put") {
        findMethodsByExactParameters(LruCacheWithListener, null, C.Object, C.Object)
                .firstOrNull()?.apply { isAccessible = true }
    }

    val SnsTimeLineUI: Class<*> by innerLazy("SnsTimeLineUI") {
        findClassesFromPackage(loader!!, classes!!, WECHAT_PACKAGE_SNS_UI)
                .filterByField("android.support.v7.app.ActionBar")
                .firstOrNull()
    }
    val SQLiteCursorFactory: Class<*> by innerLazy("SQLiteCursorFactory") {
        findClassIfExists("${WECHAT_PACKAGE_SQLITE}.database.SQLiteDatabase.CursorFactory", loader)
    }
    val SQLiteErrorHandler: Class<*> by innerLazy("SQLiteErrorHandler") {
        findClassIfExists("${WECHAT_PACKAGE_SQLITE}.DatabaseErrorHandler", loader)
    }
    val SQLiteCancellationSignal: Class<*> by innerLazy("SQLiteCancellationSignal") {
        findClassIfExists("${WECHAT_PACKAGE_SQLITE}.support.CancellationSignal", loader)
    }
    val MassSendHistoryUI: Class<*> by innerLazy("MassSendHistoryUI") {
        findClassIfExists("com.tencent.mm.plugin.masssend.ui.MassSendHistoryUI", loader)
    }

    val MassSendMsgUI: Class<*> by innerLazy("MassSendMsgUI") {
        findClassIfExists("com.tencent.mm.plugin.masssend.ui.MassSendMsgUI", loader)
    }
    val LauncherUI: Class<*> by innerLazy("LauncherUI") {
        findClassIfExists("${WECHAT_PACKAGE_UI}.LauncherUI", loader)
    }
    val MMActivity: Class<*> by innerLazy("MMActivity") {
        findClassIfExists("${WECHAT_PACKAGE_UI}.MMActivity", loader)
    }
    val MMFragmentActivity: Class<*> by innerLazy("MMFragmentActivity") {
        findClassIfExists("${WECHAT_PACKAGE_UI}.MMFragmentActivity", loader)
    }
    val MMListPopupWindow: Class<*> by innerLazy("MMListPopupWindow") {
        findClassIfExists("${WECHAT_PACKAGE_UI}.base.MMListPopupWindow", loader)
    }

    val BaseAdapter: Class<*> by innerLazy("BaseAdapter") {
        findClassIfExists("android.widget.BaseAdapter", loader)
    }

    val ActionMenuView: Class<*> by innerLazy("ActionMenuView") {
        findClassIfExists("android.support.v7.widget.ActionMenuView", loader)
    }

    val MainViewPager: Class<*> by innerLazy("MainViewPager") {
        findClassIfExists("android.support.v4.view", loader)
    }


    val HeaderViewListAdapter: Class<*> by innerLazy("HeaderViewListAdapter") {
        findClassIfExists("android.widget.HeaderViewListAdapter", loader)
    }


    // init initializes necessary information for static analysis.
    fun init(lpparam: XC_LoadPackage.LoadPackageParam, context: Context) {
        tryWithThread {
            try {
                packageName = lpparam.packageName
                loader = lpparam.classLoader
                version = getVersion(lpparam)



                density = context.resources.displayMetrics.density;
                if (lpparam.processName == "com.tencent.mm") {
                    LogTool.d("version336--->$packageName-->${version.toString()}-->density-->$density")
                }

                var apkFile: ApkFile? = null
                try {
                    apkFile = ApkFile(lpparam.appInfo.sourceDir)
                    classes = apkFile.dexClasses.map { clazz ->
                        PackageUtil.getClassName(clazz)
                    }
                } finally {
                    apkFile?.close()
                }
            } catch (t: Throwable) {
                // Ignore this one
            } finally {
                initializeChannel.done()
            }
        }
    }

    private val requireHookStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            statusLock.read {
                setResultExtras(Bundle().apply {
                    putSerializable("status", status)
                })
            }
        }
    }

    private val requireWechatPackageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            resultData = WechatPackage.toString()
        }
    }

    // listen returns debug output to the frontend.
//    fun listen(context: Context) {
//        tryWithLog {
//            context.registerReceiver(requireHookStatusReceiver, IntentFilter(ACTION_REQUIRE_HOOK_STATUS))
//            context.registerReceiver(requireWechatPackageReceiver, IntentFilter(ACTION_REQUIRE_WECHAT_PACKAGE))
//        }
//    }


    @Volatile
    var mmContext: WeakReference<Context?> = WeakReference(null)

    // getVersion returns the version of current package / application
    private fun getVersion(lpparam: XC_LoadPackage.LoadPackageParam): Version {
        val activityThreadClass = findClass("android.app.ActivityThread", null)
        val activityThread = callStaticMethod(activityThreadClass, "currentActivityThread")
        mmContext = WeakReference(callMethod(activityThread, "getSystemContext") as Context?)
        val versionName = mmContext.get()?.packageManager?.getPackageInfo(lpparam.packageName, 0)?.versionName
        return Version(versionName ?: throw Error("Cannot get Wechat version"))
    }

    // setStatus updates current status of the Wechat hooks.
    fun setStatus(key: String, value: Boolean) {
        statusLock.write {
            status[key] = value
        }
    }

    override fun toString(): String {
        val body = tryOrNull {
            this.javaClass.declaredFields.filter { field ->
                when (field.name) {
                    "INSTANCE", "\$\$delegatedProperties",
                    "initializeChannel",
                    "status", "statusLock",
                    "packageName", "loader", "version", "classes",
                    "WECHAT_PACKAGE_SQLITE",
                    "WECHAT_PACKAGE_UI",
                    "WECHAT_PACKAGE_SNS_UI",
                    "WECHAT_PACKAGE_GALLERY_UI" -> false
                    else -> true
                }
            }.joinToString("\n") {
                it.isAccessible = true
                val key = it.name.removeSuffix("\$delegate")
                var value = it.get(this)
                if (value is WeakReference<*>) {
                    value = value.get()
                }
                "$key = $value"
            }
        }

        return """====================================================
Wechat Package: ${packageName}
Wechat Version: ${version}
Module Version: ${BuildConfig.VERSION_NAME}
${body?.removeSuffix("\n") ?: "Failed to generate report."}
===================================================="""
    }
}