package com.jujing.myrobot.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 风度侠客 on 2017/1/10.
 */

object DateUtils {
    /**
     * 返回unix时间戳 (1970年至今的秒数)
     *
     * @return
     */
    val unixStamp: Long
        get() = System.currentTimeMillis() / 1000

    /**
     * 得到昨天的日期
     *
     * @return
     */
    val yestoryDate: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -1)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(calendar.time)
        }


    val getYesterdayTime: Long
        get() {

            return stringToStamp(yestoryDate)/1000
        }


    /**
     * 得到今天的日期
     *
     * @return
     */
    val todayDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }
    /**
     * 得到今天的时间
     *
     * @return
     */
    val todayTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm")
            return sdf.format(Date())
        }

    /*
     * 将String 转成时间戳
     */
    fun stringToStamp(s: String): Long {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(s)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date!!.time
    }

    /**
     * 得到日期   HH:mm
     *
     * @param timeStamp 时间戳
     * @return
     */
    fun formatDateToHour(timeStamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(timeStamp)
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     * 日期
     * @return 返回分钟
     */
    fun getMinute(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.MINUTE)
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    fun timeStampToStr(timeStamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(timeStamp)
    }

    fun timeStampToMonth(timeStamp: Long): String {
        val sdf = SimpleDateFormat("MM")
        return sdf.format(timeStamp)
    }


    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    fun formatDate(timeStamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(timeStamp)
    }

    /**
     * 得到时间  HH:mm:ss
     *
     * @param timeStamp 时间戳
     * @return
     */
    fun getTime(timeStamp: Long): String? {
        var time: String? = null
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = sdf.format(timeStamp * 1000)
        val split = date.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (split.size > 1) {
            time = split[1]
        }
        return time
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    fun convertTimeToFormat(timeStamp: Long): String {
        val curTime = System.currentTimeMillis()
        val time = (curTime - timeStamp) / 1000.toLong()

        return if (time < 60 && time >= 0) {
            "刚刚"
        } else if (time >= 60 && time < 3600) {
            (time / 60).toString() + "分钟前"
        } else if (time >= 3600 && time < 3600 * 24) {
            (time / 3600).toString() + "小时前"
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            (time / 3600 / 24).toString() + "天前"
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            (time / 3600 / 24 / 30).toString() + "个月前"
        } else if (time >= 3600 * 24 * 30 * 12) {
            (time / 3600 / 24 / 30 / 12).toString() + "年前"
        } else {
            "刚刚"
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp
     * @return
     */
    fun timeStampToFormat(timeStamp: Long): String {
        val curTime = System.currentTimeMillis() / 1000.toLong()
        val time = curTime - timeStamp
        return (time / 60).toString() + ""
    }

}