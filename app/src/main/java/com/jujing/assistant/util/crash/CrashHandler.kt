package com.jujing.assistant.util.crash

import com.jujing.assistant.util.LogTool
import java.io.PrintWriter
import java.io.StringWriter

object CrashHandler : Thread.UncaughtExceptionHandler {
    private val sDefaultHandler: Thread.UncaughtExceptionHandler? = null

    override fun uncaughtException(thread: Thread?, ex: Throwable?) {
        val sb = printCrash(ex)
        LogTool.error("uncaughtException222_log_: " + sb.toString())
        sDefaultHandler?.uncaughtException(thread, ex)




    }

    fun printCrash(ex: Throwable?): StringBuffer {
        val sb = StringBuffer()
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex?.printStackTrace(printWriter)
        var cause = ex?.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.flush()
        printWriter.close()
        val result = writer.toString()
        sb.append(result)
        return sb
    }

}