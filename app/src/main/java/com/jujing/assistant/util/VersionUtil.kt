package com.jujing.assistant.util

import android.content.Context
import android.content.pm.PackageManager
import java.io.File


object VersionUtil{

//    fun isVersionUpdate(oldVersionName:String,newVersionName:String):Boolean{
//        val oldArray = oldVersionName.split(".")
//        val newArray = newVersionName.split(".")
//        if (oldArray[0].toInt()<newArray[0].toInt()){
//            return true
//        }
//        if (oldArray[1].toInt()<newArray[1].toInt()){
//            return true
//        }
//        if (oldArray[2].toInt()<newArray[2].toInt()){
//            return true
//        }
//
//        return false
//    }



    fun isVersionUpdate(oldVersionName:String,newVersionName:String):Boolean{
        val oldArray = oldVersionName.split(".")
        val newArray = newVersionName.split(".")
        if (oldArray[0].toInt()>newArray[0].toInt()){
            return false
        }
        if (oldArray[1].toInt()>newArray[1].toInt()){
            return false
        }
        if (oldArray[2].toInt()>newArray[2].toInt()){
            return false
        }

        return true
    }

     fun getPackageNameByPath(context: Context,filePath:String):String {
//        val filePath = "/sdcard/operate1.2.7.apk"//输入APK地址
        val pm = context.packageManager
        val info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES)
        if (info != null) {
            val appInfo = info.applicationInfo
//            val appName = pm.getApplicationLabel(appInfo).toString()
            val packageName = appInfo.packageName  //获取安装包名称
            return packageName
//            LogTool.e("Abel_Test-----包名是：$packageName")
//            val version = info.versionName //获取版本信息
//            LogTool.e("Abel_Tes----版本信息：$version")

        }
         return ""
    }

    fun getPackageVersionNameByPath(context: Context,filePath:String):String {
//        val filePath = "/sdcard/operate1.2.7.apk"//输入APK地址
        val pm = context.packageManager
        val info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES)
        if (info != null) {
//            val appInfo = info.applicationInfo
//            val appName = pm.getApplicationLabel(appInfo).toString()
//            LogTool.e("Abel_Test-----包名是：$packageName")
            val version = info.versionName //获取版本信息
//            LogTool.e("Abel_Tes----版本信息：$version")
            return version

        }
        return ""
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本
     */
    fun getVersionName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_CONFIGURATIONS)
            return packageInfo.versionName

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }


    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_CONFIGURATIONS)
            return packageInfo.versionCode

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return -1
    }



//    fun getSiriVersion(context: Context): String {
//        val packageManager = context.packageManager
//        val packageInfoList = packageManager.getInstalledPackages(0)
//
//        for (packageInfo in packageInfoList) {
//            if ("com.siri.soujuanjuan" == packageInfo.packageName) {
//                return packageInfo.versionName
//            }
//        }
//        return "-1"
//    }




}