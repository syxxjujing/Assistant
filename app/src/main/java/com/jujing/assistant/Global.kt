package com.jujing.assistant

import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import com.jujing.assistant.util.LogTool
import kotlin.concurrent.thread

object Global {


    const val XPOSED_CODE = "127"

    const val XPOSED_PACKAGE_NAME = "de.robv.android.xposed.installer"
    const val WECHAT_PACKAGE_NAME = "com.tencent.mm"
    const val MAGICIAN_PACKAGE_NAME = "com.jujing.assistant"
    val storage = Environment.getExternalStorageDirectory().absolutePath + "/11xai"
    val storage_siri = Environment.getExternalStorageDirectory().absolutePath + "/11Siri"
    var STORAGE_XPOSED_CODE = "${Global.storage}/xposed_code.txt"
    const val XPOSED_FILE_PROVIDER = "$XPOSED_PACKAGE_NAME.fileprovider"
    const val MAGICIAN_FILE_PROVIDER = "$MAGICIAN_PACKAGE_NAME.files"
    @SuppressLint("SdCardPath")
    private val DATA_DIR = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) "/data/data/" else "/data/user_de/0/"
    val XPOSED_BASE_DIR = "$DATA_DIR/$XPOSED_PACKAGE_NAME/"
    val MAGICIAN_BASE_DIR = "$DATA_DIR/$MAGICIAN_PACKAGE_NAME/"
    const val ACTION_TEXT = "cn.myrobot.intent.ACTION_TEXT"
    const val EXTRA_MESSAGE = "extra_message"


    var STORAGE_USER_TOKEN = "${Global.storage_siri}/user_token.txt"
    const val ACTION_SIRI_REBOOT = "action_siri_reboot"

    var STORAGE_SETTINGS_MODE = Environment.getExternalStorageDirectory().absolutePath + "/11Siri/storage_settings_mode.txt"


    var STORAGE_INTERVAL_TIME = Environment.getExternalStorageDirectory().absolutePath + "/11Siri/interval_time.txt"

    var STORAGE_GOOD_INTERVAL_TIME = Environment.getExternalStorageDirectory().absolutePath + "/11Siri/good_interval_time.txt"

    var STORAGE_AUTO_REPLY = "${Global.storage}/auto_reply.txt"


    var STORAGE_MAIN_WXID = "${Global.storage}/main_wxid.txt"

    var STORAGE_GROUP_PREFIX = "${Global.storage}/group_prefix.txt"


    var STORAGE_STOP_SEND_TIME = "${Global.storage_siri}/stop_send_time.txt"


    var STORAGE_VERSION_CODE = "${Global.storage}/version_code.txt"

    var STORAGE_SIRI_VERSION_CODE = "${Global.storage_siri}/version_code.txt"


    fun tryWithLog(func: () -> Unit) {
        try {
            func()
        } catch (t: Throwable) {
//            XposedBridge.log(t)
            LogTool.d("ttt86-->$t")

        }
    }

    fun <T> tryOrNull(func: () -> T): T? =
            try {
                func()
            } catch (t: Throwable) {
//                XposedBridge.log(t); null
                LogTool.d("ttt96-->$t");null
            }

    fun tryWithThread(func: () -> Unit): Thread {
        return thread(start = true) { func() }.apply {
            setUncaughtExceptionHandler { _, t -> LogTool.d("tryWithThread107---->${t.toString()}") }
        }
    }


}