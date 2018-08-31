package com.jujing.assistant.util

import com.jujing.assistant.Global
import java.io.*

object WriteFileUtil{

    //使用FileInputStream读取文件
    fun read(path: String): String {
        try {
            val file = File(path)
            val `is` = FileInputStream(file)
            val b = ByteArray(`is`.available())
            `is`.read(b)
            return String(b)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }


//    fun writeLog(info: String,context: Context){
//
//        LogTool.d("打印的日志!!!--->$info")
//        val localLog = LogTool.read100Line(Global.STORAGE_APP_LOG)
//
//        val df = SimpleDateFormat("HH:mm:ss")
//        val date = "[" + df.format(Date()) + "]:  "
//
//        val log = date+info+"\n"+localLog
//        wrieFileByBufferedWriter(log,Global.STORAGE_APP_LOG)
//
//        context.sendBroadcast(Intent().setAction(Global.ACTION_APP_LOG))
//
//    }





    fun wrieFileByBufferedWriter(info: String, fileName: String) {
        try {
            val fullFile = File(fileName)
            fullFile.parentFile.mkdirs()
//            fullFile.parentFile.parentFile.mkdirs()

            //第二个参数意义是说是否以append方式添加内容
            val bw = BufferedWriter(FileWriter(fullFile, false))
            bw.write(info)
            bw.flush()
//            Log.d(TAG, "写入成功")
        } catch (e: Exception) {
            e.printStackTrace()
//            Log.d(TAG, "e:" + e)
        }

    }


    fun readMainWxid():String{
        val mainWxid = read(Global.STORAGE_MAIN_WXID)
        if (mainWxid==""){
            return "laibujile001"
        }


        return mainWxid

    }


    fun addZero(a: Int): String {
        return if (a < 10) {
            "0$a"
        } else {
            a.toString() + ""
        }
    }



}