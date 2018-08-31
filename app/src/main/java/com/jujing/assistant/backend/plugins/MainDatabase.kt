package com.jujing.assistant.backend.plugins

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import de.robv.android.xposed.XposedHelpers

object MainDatabase {

    //这个关键字用于多线程，可以视为「轻量化的 synchronized」。Kotlin 中用 @Volatile 标注。
    @Volatile
    var mainDB: Any? = null


    @Volatile
    var myUsername:String? = null
    @Volatile
    var myNickname:String? = null
    @Volatile
    var myAlias:String? = null

    @Volatile
    var myHeadUrl:String? = null

    // snsDB is the database that stores SNS information.
    @Volatile var snsDB: Any? = null


    @Volatile var favDB: Any? = null


    @Volatile
    var operateDB: SQLiteDatabase? = null


    fun cleanUnreadCount() {
        val database = mainDB ?: return
        val clean = ContentValues().apply {
            put("unReadCount", 0)
            put("unReadMuteCount", 0)
        }
        XposedHelpers.callMethod(database, "update", "rconversation", clean, null, null)
    }
}