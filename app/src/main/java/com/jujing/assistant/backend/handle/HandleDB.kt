package com.jujing.assistant.backend.handle

import com.jujing.assistant.backend.plugins.MainDatabase
import com.jujing.assistant.util.LogTool
import com.jujing.assistant.util.ShellUtils.num
import de.robv.android.xposed.XposedHelpers
/**
 * 如有疑问，请加QQ群：536941874
 *
 * 作者微信：laibujile001
 *
 * */
object HandleDB {


    fun queryContactLabel(): ArrayList<String>? {
        var list = ArrayList<String>()
        val database = MainDatabase.mainDB ?: return null
        val sql = "select labelName,labelID from ContactLabel"
        var cursor: Any? = null
        try {
            cursor = XposedHelpers.callMethod(database, "rawQuery", sql, null)
            val count = XposedHelpers.callMethod(cursor, "getCount") as Int
//            LogTool.e("545454545454--->$count")
            (0 until count).map {
                XposedHelpers.callMethod(cursor, "moveToNext")
                val labelName = XposedHelpers.callMethod(cursor, "getString", 0) as String
//                val labelID = XposedHelpers.callMethod(cursor, "getInt", 1).toString()

//                val num = queryNumByRContact(labelID)
//                list.add("$labelName($num)")
                list.add(labelName)
            }

            return list
        } catch (e: Exception) {
            LogTool.d("eee65 -->$e")
        } finally {
            if (cursor != null) {
                XposedHelpers.callMethod(cursor, "close")
            }
        }

        return null
    }


    fun queryLabelID(labelName:String):String{
        val database = MainDatabase.mainDB ?: return ""
        val sql = "select labelID from ContactLabel where labelName = '$labelName'"
        var cursor: Any? = null
        try {
            cursor = XposedHelpers.callMethod(database, "rawQuery", sql, null)
            val count = XposedHelpers.callMethod(cursor, "getCount") as Int
            (0 until count).map {
                XposedHelpers.callMethod(cursor, "moveToNext")
                val labelID = XposedHelpers.callMethod(cursor, "getString", 0) as String
                return queryRContact(labelID)
            }

        } catch (e: Exception) {
        } finally {
            if (cursor != null) {
                XposedHelpers.callMethod(cursor, "close")
            }
        }
        return ""

    }

    fun queryRContact(labelID: String):String{
        val database = MainDatabase.mainDB ?: return ""
        val sql = "select contactLabelIds,username from rcontact"
        var cursor: Any? = null
        var wxids = ""
        try {
            cursor = XposedHelpers.callMethod(database, "rawQuery", sql, null)
            val count = XposedHelpers.callMethod(cursor, "getCount") as Int
            (0 until count).map {
                XposedHelpers.callMethod(cursor, "moveToNext")
                val contactLabelIds = XposedHelpers.callMethod(cursor, "getString", 0) as String
                val username = XposedHelpers.callMethod(cursor, "getString", 1) as String
                val array = contactLabelIds.split(",")
                if (array.contains(labelID)){
                    wxids = "$wxids;$username"
                }
            }


            return wxids.substring(1,wxids.length)


        } catch (e: Exception) {
        } finally {
            if (cursor != null) {
                XposedHelpers.callMethod(cursor, "close")
            }
        }
        return ""
    }


    fun queryNumByRContact(labelID: String) :Int{
        val database = MainDatabase.mainDB ?: return -1
        val sql = "select contactLabelIds from rcontact"
        var cursor: Any? = null
        var num = 0
        try {
            cursor = XposedHelpers.callMethod(database, "rawQuery", sql, null)
            val count = XposedHelpers.callMethod(cursor, "getCount") as Int
//            LogTool.e("545454545454--->$count")
            (0 until count).map {
                XposedHelpers.callMethod(cursor, "moveToNext")
                val contactLabelIds = XposedHelpers.callMethod(cursor, "getString", 0) as String
                val array = contactLabelIds.split(",")
                if (array.contains(labelID)){
                    num += 1
                }
            }

            return num

        } catch (e: Exception) {
            LogTool.d("eee65 -->$e")
        } finally {
            if (cursor != null) {
                XposedHelpers.callMethod(cursor, "close")
            }
        }

        return -1


    }


}