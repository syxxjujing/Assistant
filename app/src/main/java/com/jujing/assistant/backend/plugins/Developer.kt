package com.jujing.assistant.backend.plugins

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.content.ContextCompat.startActivity
import android.widget.ListAdapter
import com.jujing.assistant.backend.WechatPackage
import com.jujing.assistant.util.LogTool
import com.jujing.myrobot.C
import com.jujing.myrobot.util.MessageUtil.bundleToString
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedBridge.hookAllConstructors
import de.robv.android.xposed.XposedBridge.log
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findAndHookConstructor
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import java.io.File
import java.lang.ref.WeakReference
import java.lang.reflect.Method
import java.util.regex.Pattern

/**
 * 如有疑问，请加QQ群：536941874
 *
 * 作者微信：laibujile001
 *
 * */
object Developer {


    var isDBTest = false //TODO 测试
    var isActivityTest = false //TODO 测试


    @Volatile
    var baseActivity: WeakReference<Activity?> = WeakReference(null)
    private val pkg = WechatPackage


    // Hook Activity.startActivity and Activity.onCreate to trace activities.
    @JvmStatic
    fun traceActivities() {
        findAndHookMethod("android.app.Activity", pkg.loader,
                "startActivityForResult", C.Intent, C.Int, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val intent = param.args[0] as Intent?
                val requestCode = param.args[1] as Int
                if (isActivityTest) {
                    LogTool.e("activity_log---->Activity.startActivityForResult => " +
                            "${param.thisObject.javaClass}, " +
                            "intent => ${bundleToString(intent?.extras)}, " +
                            "requestCode---->$requestCode")
                }

            }
        })
        findAndHookMethod(
                "android.app.Activity", pkg.loader,
                "startActivity", C.Intent, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val intent = param.args[0] as Intent?
                if (isActivityTest) {
                    LogTool.e("activity_log---->Activity.startActivity => " +
                            "${param.thisObject.javaClass}, " +
                            "intent => ${bundleToString(intent?.extras)}")
                }
            }
        })

        findAndHookMethod(
                "android.app.Activity", pkg.loader,
                "startActivity", C.Intent, C.Bundle, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val intent = param.args[0] as Intent?
                val bundle = param.args[1] as Bundle?
                if (isActivityTest) {
                    LogTool.e("activity_log---->Activity.startActivity => " +
                            "${param.thisObject.javaClass}, " +
                            "intent => ${bundleToString(intent?.extras)}," +
                            "bundle => ${bundleToString(bundle)}")
                }
            }
        })

        findAndHookMethod(
                "android.app.Activity", pkg.loader,
                "onCreate", C.Bundle, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val bundle = param.args[0] as Bundle?
                val intent = (param.thisObject as Activity).intent
                if (isActivityTest) {
                    LogTool.e("activity_log----->Activity.onCreate => " +
                            "${param.thisObject.javaClass}, " +
                            "intent => ${bundleToString(intent?.extras)}, " +
                            "bundle => ${bundleToString(bundle)}")
                }


//                    currentActivityName = param.thisObject.javaClass.name
            }
        })

        findAndHookMethod("android.app.Activity", pkg.loader,
                "onResume", object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val activityName = param.thisObject.javaClass.name
                val activity = param.thisObject as Activity?
                baseActivity = WeakReference(activity)

            }
        })


    }


    // Hook SQLiteDatabase to trace all the database operations.
    @JvmStatic
    fun traceDatabase() {

//        if (preferences!!.getBoolean(DEVELOPER_DATABASE_QUERY, true)) {
        findAndHookMethod(
                pkg.SQLiteDatabase, "rawQueryWithFactory",
                pkg.SQLiteCursorFactory, C.String, C.StringArray, C.String, pkg.SQLiteCancellationSignal, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val sql = param.args[1] as String?
                val selectionArgs = param.args[2] as Array<*>?
                val path = param.thisObject.toString()
                if (path.endsWith("EnMicroMsg.db")) {
                    if (MainDatabase.mainDB !== param.thisObject) {
                        MainDatabase.mainDB = param.thisObject
                    }
                }

            }
        })
//        }

//        if (preferences!!.getBoolean(DEVELOPER_DATABASE_INSERT, true)) {
        findAndHookMethod(
                pkg.SQLiteDatabase, "insertWithOnConflict",
                C.String, C.String, C.ContentValues, C.Int, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                try {
                    val table = param.args[0] as String?
//                        val str = param.args[1] as String?
                    val values = param.args[2] as ContentValues?


                } catch (e: Exception) {
                    e.printStackTrace()

                }


            }
        })
//        }

//        if (preferences!!.getBoolean(DEVELOPER_DATABASE_UPDATE, true)) {
        findAndHookMethod(
                pkg.SQLiteDatabase, "updateWithOnConflict",
                C.String, C.ContentValues, C.String, C.StringArray, C.Int, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam?) {

            }

            override fun beforeHookedMethod(param: MethodHookParam) {
                try {
                    val table = param.args[0] as String?
                    val values = param.args[1] as ContentValues?
                    val whereClause = param.args[2] as String?
                    val whereArgs = param.args[3] as Array<*>?


//                    if (table == "chatroom") {
//                        LogTool.ed("DB => update " +
//                                "table = $table, " +
//                                "values = $values, " +
//                                "whereClause = $whereClause, " +
//                                "whereArgs = ${argsToString(whereArgs)}, " +
//                                "db = ${param.thisObject}")//TODO 测试
//                    }


                } catch (e: Exception) {
//                        LogTool.d("eee257---->$e")
                }

            }


        })
//        }

    }


    // Hook Log to trace hidden logcat output.

    // Hook FileInputStream / FileOutputStream to trace file operations.

    // Hook XML Parser to trace the XML files used in Wechat.

}