package com.jujing.assistant.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vampire on 2017/11/3.
 */

public class CircleUtil {

    public static List<String> getPictureName(String str) {
        List<String> list = new ArrayList<>();
        String aasString = "(12){1}[0-9]{18}";
        Pattern pattern = Pattern.compile(aasString);
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        list.remove(0);
        return list;
    }

    /**
     * 取出两字符串中间的字符(不包含两边的字符)
     */
    public static String betweenTwoStr(String str1, String str2, String str) {
        String regex = str1 + "(.*)" + str2;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    /**
     * 获取链接类型的链接
     */
    public static String getCircleUrl(String str) {

        String regex = "http(.*)\\*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {

            return matcher.group();
        }
        return "";
    }


    public static String getSMSCode(String str) {
        Pattern pp = Pattern.compile("\\((\\w+)\\)");
        Matcher m = pp.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return "";

    }


//    public static String filterSymbol(String str){
//        String regex = "[^\\u4E00-\\u9FA5]";
//         str = str.replaceAll(regex,"");
//        return str;
//    }
}
