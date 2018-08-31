package com.jujing.assistant.backend

import android.content.Context
import android.content.res.XModuleResources
import android.os.Build
import com.jujing.assistant.Global.MAGICIAN_PACKAGE_NAME
import com.jujing.assistant.Global.tryWithLog
import com.jujing.assistant.Global.tryWithThread
import com.jujing.assistant.backend.plugins.Developer
import com.jujing.assistant.backend.plugins.HookClazz
import com.jujing.assistant.backend.plugins.SnsForward
import com.jujing.assistant.util.LogTool
import com.jujing.myrobot.C
import dalvik.system.PathClassLoader
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File

/**
 * 如有疑问，请加QQ群：536941874
 *
 * 作者微信：laibujile001
 *
 * */
class WechatHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        tryWithLog {

            when (lpparam?.packageName) {
                MAGICIAN_PACKAGE_NAME ->
                    hookApplicationAttach(lpparam.classLoader, { _ ->
                        handleLoadMagician(lpparam.classLoader)
                    })


                else -> if (isWechat(lpparam!!)) {
                    hookApplicationAttach(lpparam.classLoader, { context ->
                        handleLoadWechatOnFly(lpparam, context)
                    })
                }

            }


        }
    }


    public fun handleLoadWechat(lpparam: XC_LoadPackage.LoadPackageParam, context: Context) {
        WechatPackage.init(lpparam, context)

//        val pluginDatabase = Database
//        tryHook(pluginDatabase::hookDatabase)//HOOK 微信的db

        val pluginDeveloper = Developer
        tryHook(pluginDeveloper::traceActivities)//HOOK activity
        tryHook(pluginDeveloper::traceDatabase)//HOOK 微信的db

        val hookClazz = HookClazz
        tryHook(hookClazz::hook)

        val snsForward = SnsForward
        tryHook(snsForward::hookSnsTimeLineUI)


        loadModuleResource(context)

    }

    companion object {
        @Volatile
        var MODULE_RES: XModuleResources? = null
    }

    private fun loadModuleResource(context: Context) {
        tryWithThread {
            val path = findAPKPath(context, MAGICIAN_PACKAGE_NAME)
            MODULE_RES = XModuleResources.createInstance(path, null)
        }
    }

    @Suppress("DEPRECATION")
    private fun handleLoadMagician(loader: ClassLoader) {
        XposedHelpers.findAndHookMethod(
                "$MAGICIAN_PACKAGE_NAME.frontend.MainActivity", loader,
                "isModuleLoaded", object : XC_MethodReplacement() {
            override fun replaceHookedMethod(param: MethodHookParam): Any = true
        })
    }

    private inline fun hookApplicationAttach(loader: ClassLoader, crossinline callback: (Context) -> Unit) {
        XposedHelpers.findAndHookMethod("android.app.Application", loader, "attach", C.Context, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                callback(param.thisObject as Context)
            }
        })
    }

    // isWechat returns true if the current application seems to be Wechat.
    private fun isWechat(lpparam: XC_LoadPackage.LoadPackageParam): Boolean {
        val features = listOf(
                "libwechatcommon.so",
                "libwechatmm.so",
                "libwechatnetwork.so",
                "libwechatsight.so",
                "libwechatxlog.so"
        )

        return try {
            val libraryDir = File(lpparam.appInfo.nativeLibraryDir)
//            val path = libraryDir.path
//            LogTool.d("path63------>" + path)

            val hits = features.filter { filename ->
                File(libraryDir, filename).exists()

            }.size
            (hits.toDouble() / features.size) > 0.5f
        } catch (e: Exception) {
            false
        }


    }

    ///data/app/com.keye.operate-1.apk  (apk的路径)
    private fun findAPKPath(context: Context, packageName: String) =
            context.packageManager.getApplicationInfo(packageName, 0).publicSourceDir

    // handleLoadWechatOnFly uses reflection to load updated module without reboot.
    private fun handleLoadWechatOnFly(lpparam: XC_LoadPackage.LoadPackageParam, context: Context) {
        val path = findAPKPath(context, MAGICIAN_PACKAGE_NAME)
//        LogTool.d("apkPath94---->"+path)
        if (!File(path).exists()) {
            LogTool.d("Cannot load module on fly: APK not found")
            return
        }
        val pathClassLoader = PathClassLoader(path, ClassLoader.getSystemClassLoader())
        val clazz = Class.forName("$MAGICIAN_PACKAGE_NAME.backend.WechatHook", true, pathClassLoader)
        val method = clazz.getDeclaredMethod("handleLoadWechat", lpparam.javaClass, Context::class.java)
        method.isAccessible = true
        method.invoke(clazz.newInstance(), lpparam, context)
    }


    // NOTE: For Android 7.X or later, multi-thread and lazy initialization
    //       causes unexpected crashes with WeXposed. So I fall back to the
    //       original logic for now.
    private inline fun tryHook(crossinline hook: () -> Unit) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> tryWithLog { hook() }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> tryWithThread { hook() }
            else -> tryWithThread {
                try {
                    hook()
                } catch (t: Throwable) { /* Ignore */
                    LogTool.d("eee166-->" + t)
                }
            }
        }
    }
}