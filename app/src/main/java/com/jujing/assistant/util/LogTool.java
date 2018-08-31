package com.jujing.assistant.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTool {

    private static final String TAG = "my_xai";

    public static void e(String info) {
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        String date = "[" + df.format(new Date()) + "]:  ";
//        wrieFileByBufferedWriter(date + info + "\n", "tntdata_pointer_log/", getTodayDate());
        Log.e(TAG+"_e", info);
    }

    public static void d(String info) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String date = "[" + df.format(new Date()) + "]:  ";
        wrieFileByBufferedWriter(date + info + "\n", "operate_xai/", getTodayDate());
        Log.e(TAG+"_d", info);


    }



    public static void single(String info) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String date = "[" + df.format(new Date()) + "]:  ";
        wrieFileByBufferedWriter(date + info + "\n", "operate_single/", getTodayDate());
        Log.e(TAG+"_d", info);
    }
    public static void group(String info) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String date = "[" + df.format(new Date()) + "]:  ";
        wrieFileByBufferedWriter(date + info + "\n", "operate_group/", getTodayDate());
        Log.e(TAG+"_d", info);
    }

    public static void error(String info) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String date = "[" + df.format(new Date()) + "]:  ";
        wrieFileByBufferedWriter(date + info + "\n", "operate_error/", getTodayDate());
        Log.e(TAG+"_error", info);
    }



    public static void wrieFileByBufferedWriter(String info, String dirName,String fileName) {
        try {
//            File file = new File("storage/emulated/0/Download/auto/" + fileName + ".txt");
//            File file = new File("storage/emulated/0/Download/auto_log/"+dirName);
//            if (!file.exists()){
//                file.mkdirs();
//            }


            File fullFile = new File("storage/emulated/0/Download/auto_log_xai/" +dirName+ fileName + ".txt");
            fullFile.getParentFile().mkdirs();
            //第二个参数意义是说是否以append方式添加内容
            BufferedWriter bw = new BufferedWriter(new FileWriter(fullFile, true));
            bw.write(info);
            bw.flush();
            Log.d(TAG, "写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "e:" + e);
        }
    }

    /**
     * 得到今天的日期
     *
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }




    //使用BufferReader读取文件
    public static String read100Line(String path) {
        try {

            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
//            StringBuffer sb = new StringBuffer();
            int line = 0;
            String result = "";
            while ((readline = br.readLine()) != null) {
                if (line==100){
//                    LogTool.e("超过100个了!!");
                    break;
                }

                result = result +readline+"\n";
                line++;
            }
            br.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
