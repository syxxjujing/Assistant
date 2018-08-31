package com.jujing.assistant.util.crash



class AbstractCrashReportHandler(){
    init {
        val handler = CrashHandler
        Thread.setDefaultUncaughtExceptionHandler(handler)
    }

}