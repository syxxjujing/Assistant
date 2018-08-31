package com.jujing.assistant.backend.plugins

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.jujing.assistant.Global
import com.jujing.assistant.R
import com.jujing.assistant.backend.WechatHook
import com.jujing.assistant.backend.WechatPackage
import com.jujing.assistant.backend.handle.HandleDB
import com.jujing.assistant.backend.plugins.Developer.baseActivity
import com.jujing.assistant.util.LogTool
import com.jujing.assistant.util.crash.CrashHandler
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * 如有疑问，请加QQ群：536941874
 *
 * 作者微信：laibujile001
 *
 * */
object HookClazz {
    private val pkg = WechatPackage

    @JvmStatic
    fun hook() {
        XposedHelpers.findAndHookMethod(pkg.LauncherUI, "onCreateOptionsMenu", Menu::class.java, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                super.beforeHookedMethod(param)
                val menu = param.args[0] as Menu
//                menu.add(0, 3, 0, "群发助手")
                menu.add(0, 3, 0, "按标签群发")

                menu.getItem(0).setOnMenuItemClickListener {
                    //dialog 必须是V4的
                    try {
                        val builder = AlertDialog.Builder(baseActivity.get()!!)
                        val dialog = builder.create()
                        val layout = WechatHook.MODULE_RES?.getLayout(R.layout.dialog_label)
                        val view = baseActivity.get()?.layoutInflater?.inflate(layout, null)
                        val lv = view?.findViewById<ListView>(R.id.lv_label) as ListView
                        val list = HandleDB.queryContactLabel()
                        //数组适配器
                        val adapter = ArrayAdapter(baseActivity.get()!!, android.R.layout.simple_list_item_1, list?.toArray())
                        lv.adapter = adapter

                        lv.setOnItemClickListener { adapterView, view, i, l ->
                            baseActivity.get()?.runOnUiThread {
                                showProgress()
                            }
                            Global.tryWithThread {//防止UI卡顿，写到子线程中
                                val labelName = list!![i]
                                val result = HandleDB.queryLabelID(labelName)

                                baseActivity.get()?.runOnUiThread {
                                    hideProgress()
                                }
                                if (result == "") {
                                    baseActivity.get()?.runOnUiThread {
                                        Toast.makeText(baseActivity.get()!!, "此标签没有好友!", Toast.LENGTH_SHORT).show()
                                    }
                                    return@tryWithThread
                                }
                                baseActivity.get()?.startActivity(Intent(baseActivity.get(), pkg.MassSendMsgUI)
                                        .putExtra("mass_send_contact_list", result)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            }
                        }


                        dialog.setView(view)
                        dialog.show()
                    } catch (e: Throwable) {
                        LogTool.d("eee97---->${CrashHandler.printCrash(e)}")
                    }



                    return@setOnMenuItemClickListener false
                }
            }
        })


    }


    private var mDialog: Dialog? = null
    /**
     * 创建进度条对话框
     */
    fun onCreateProgressDialog(): Dialog {
        val dialog = ProgressDialog(baseActivity.get())
        dialog.setMessage("请稍等......")
        //设置进度条是否可以按退回键取消  
        dialog.setCancelable(false)
        //设置点击进度对话框外的区域对话框不消失
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    /**
     * 显示进度条对话框
     */
    fun showProgress() {
        if (mDialog == null) {
            mDialog = onCreateProgressDialog()
            if (mDialog == null) {
                return
            }
        }
        try {
            mDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 隐藏进度条对话框
     */
    fun hideProgress() {
        if (isProgressing()) {
            mDialog?.dismiss()
        }
    }


    /**
     * 进度条是否在显示
     */
    fun isProgressing(): Boolean {
        return mDialog != null && mDialog!!.isShowing
    }

}