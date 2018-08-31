package com.jujing.assistant.util.javafile;////
//// Decompiled by Jadx - 3386ms
////
//package android.support.v7.view.menu;
//
//import android.app.Activity;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.CompressFormat;
//import android.graphics.Bitmap.Config;
//import android.graphics.Canvas;
//import android.media.MediaMetadataRetriever;
//import android.os.Bundle;
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import mytestdemo.jionego.com.mytestdemo.a.a;
//import mytestdemo.jionego.com.mytestdemo.a.b;
//import mytestdemo.jionego.com.mytestdemo.a.d;
//import mytestdemo.jionego.com.mytestdemo.a.e;
//
//public class aa {
//    public static Activity a = null;
//    private static Class<?> b;
//
//    public static void a() {
//        XposedHelpers.findAndHookMethod(a.a, y.e.classLoader, "onCreate", new Object[]{Bundle.class, new 1()});
//    }
//
//    private static void a(ClassLoader classLoader) {
//        if (b == null) {
//            try {
//                b = XposedHelpers.findClass(y.a.e, classLoader);
//            } catch (Throwable th) {
//            }
//        }
//    }
//
//    public static void a(String str) {
//        XposedHelpers.callMethod(a, e.a, new Object[]{str, "null", Integer.valueOf(0)});
//    }
//
//    public static void a(String str, String str2) {
//        Object newInstance;
//        ConcurrentHashMap concurrentHashMap;
//        Collection collection;
//        Exception e;
//        ArrayList arrayList;
//        Cursor b;
//        Exception exception;
//        Class findClass;
//        String a;
//        Collection values;
//        int i;
//        int i2;
//        Object objectField;
//        Class findClass2;
//        String str3;
//        long longValue;
//        Object objectField2;
//        Object objectField3;
//        String a2;
//        Object newInstance2;
//        Cursor b2;
//        ArrayList arrayList2;
//        String a3;
//        try {
//            findClass = XposedHelpers.findClass(k.k, y.e.classLoader);
//            XposedBridge.log("m:" + findClass);
//            newInstance = XposedHelpers.newInstance(findClass, new Object[0]);
//            try {
//                XposedBridge.log("newM:" + newInstance);
//                a = b.a("versionName", "");
//                XposedBridge.log("version11:" + a);
//                if (!"".equals(a) && a != null) {
//                    XposedBridge.log("版本:" + a.equals("6.5.16"));
//                    if (a.equals("6.5.10") || a.equals("6.5.16") || a.equals("6.6.1") || a.equals("6.6.3") || a.equals("6.6.5")) {
//                        XposedHelpers.callMethod(newInstance, k.m, new Object[]{Integer.valueOf(0), Integer.valueOf(0), str, str2, Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                        new ArrayList().add(str);
//                        XposedHelpers.callMethod(newInstance, k.n, new Object[]{r3, Boolean.valueOf(true), Integer.valueOf(0), Integer.valueOf(0), str2, Integer.valueOf(0x7f0201ae)});
//                        try {
//                            concurrentHashMap = (ConcurrentHashMap) XposedHelpers.getObjectField(newInstance, k.o);
//                            XposedBridge.log("cQk1:" + concurrentHashMap);
//                            values = concurrentHashMap.values();
//                            try {
//                                XposedBridge.log("m_e:" + values);
//                                collection = values;
//                            } catch (Exception e2) {
//                                e = e2;
//                                e.printStackTrace();
//                                collection = values;
//                                i = 0;
//                                for (Object objectField4 : collection) {
//                                    if (i == 0) {
//                                        i2 = i + 1;
//                                        try {
//                                            XposedBridge.log("object:" + objectField4);
//                                            XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField4, k.p)));
//                                            XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField4, k.q)).intValue());
//                                            XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField4, k.r)).intValue());
//                                            XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField4, k.s)).intValue());
//                                            findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                                            XposedBridge.log("pstring:" + findClass2);
//                                            XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                                            XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                                            XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                                            str3 = (String) XposedHelpers.getObjectField(objectField4, k.x);
//                                            XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField4, k.w)));
//                                            XposedBridge.log("cQC:" + str3);
//                                            longValue = ((Long) XposedHelpers.getObjectField(objectField4, k.y)).longValue();
//                                            objectField2 = XposedHelpers.getObjectField(objectField4, k.z);
//                                            objectField3 = XposedHelpers.getObjectField(objectField4, k.A);
//                                            objectField4 = XposedHelpers.getObjectField(objectField4, k.B);
//                                            XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField4});
//                                            findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                                            XposedBridge.log("h:" + findClass);
//                                            newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                                            XposedBridge.log("newh:" + newInstance2);
//                                            arrayList = new ArrayList();
//                                            b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                                            while (b2.moveToNext()) {
//                                                arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                                            }
//                                            if (arrayList.size() == 0) {
//                                                arrayList.add(Integer.valueOf(2));
//                                            }
//                                            a2 = b.a("myWechatID", "");
//                                            if (a2.equals("")) {
//                                                b = g.b("select value from userinfo where id='2'");
//                                                while (b.moveToNext()) {
//                                                    a2 = b.getString(b.getColumnIndex("value"));
//                                                }
//                                            }
//                                            b2.close();
//                                            XposedBridge.log("list_type:" + arrayList);
//                                            arrayList2 = new ArrayList();
//                                            XposedBridge.log("imagepath:" + arrayList2);
//                                            arrayList2.add(str);
//                                            a3 = b.a("versionName", "");
//                                            XposedBridge.log("version:" + a3);
//                                            if (!a3.equals("6.5.10")) {
//                                            }
//                                            XposedBridge.log("version111:" + a3);
//                                            XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                                            XposedBridge.log("发送图片成功6.5.10");
//                                            XposedBridge.log("发送图片成功");
//                                            b.b("imgSwitch", "true");
//                                        } catch (Exception e3) {
//                                            e3.printStackTrace();
//                                        } finally {
//                                            collection.clear();
//                                        }
//                                        i = i2;
//                                    }
//                                }
//                            }
//                        } catch (Exception e32) {
//                            exception = e32;
//                            values = null;
//                            e = exception;
//                            e.printStackTrace();
//                            collection = values;
//                            i = 0;
//                            for (Object objectField42 : collection) {
//                                if (i == 0) {
//                                    i2 = i + 1;
//                                    XposedBridge.log("object:" + objectField42);
//                                    XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField42, k.p)));
//                                    XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField42, k.q)).intValue());
//                                    XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField42, k.r)).intValue());
//                                    XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField42, k.s)).intValue());
//                                    findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                                    XposedBridge.log("pstring:" + findClass2);
//                                    XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                                    XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                                    XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                                    str3 = (String) XposedHelpers.getObjectField(objectField42, k.x);
//                                    XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField42, k.w)));
//                                    XposedBridge.log("cQC:" + str3);
//                                    longValue = ((Long) XposedHelpers.getObjectField(objectField42, k.y)).longValue();
//                                    objectField2 = XposedHelpers.getObjectField(objectField42, k.z);
//                                    objectField3 = XposedHelpers.getObjectField(objectField42, k.A);
//                                    objectField42 = XposedHelpers.getObjectField(objectField42, k.B);
//                                    XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField42});
//                                    findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                                    XposedBridge.log("h:" + findClass);
//                                    newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                                    XposedBridge.log("newh:" + newInstance2);
//                                    arrayList = new ArrayList();
//                                    b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                                    while (b2.moveToNext()) {
//                                        arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                                    }
//                                    if (arrayList.size() == 0) {
//                                        arrayList.add(Integer.valueOf(2));
//                                    }
//                                    a2 = b.a("myWechatID", "");
//                                    if (a2.equals("")) {
//                                        b = g.b("select value from userinfo where id='2'");
//                                        while (b.moveToNext()) {
//                                            a2 = b.getString(b.getColumnIndex("value"));
//                                        }
//                                    }
//                                    b2.close();
//                                    XposedBridge.log("list_type:" + arrayList);
//                                    arrayList2 = new ArrayList();
//                                    XposedBridge.log("imagepath:" + arrayList2);
//                                    arrayList2.add(str);
//                                    a3 = b.a("versionName", "");
//                                    XposedBridge.log("version:" + a3);
//                                    if (a3.equals("6.5.10")) {
//                                    }
//                                    XposedBridge.log("version111:" + a3);
//                                    XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                                    XposedBridge.log("发送图片成功6.5.10");
//                                    XposedBridge.log("发送图片成功");
//                                    b.b("imgSwitch", "true");
//                                    i = i2;
//                                }
//                            }
//                        }
//
//
//
//
//
//                        i = 0;
//                        for (Object objectField422 : collection) {
//                            if (i == 0) {
//                                i2 = i + 1;
//                                XposedBridge.log("object:" + objectField422);
//                                XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField422, k.p)));
//                                XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField422, k.q)).intValue());
//                                XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField422, k.r)).intValue());
//                                XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField422, k.s)).intValue());
//                                findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                                XposedBridge.log("pstring:" + findClass2);
//                                XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                                XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                                XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                                str3 = (String) XposedHelpers.getObjectField(objectField422, k.x);
//
//                                XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField422, k.w)));
//                                XposedBridge.log("cQC:" + str3);
//                                longValue = ((Long) XposedHelpers.getObjectField(objectField422, k.y)).longValue();
//                                objectField2 = XposedHelpers.getObjectField(objectField422, k.z);
//                                objectField3 = XposedHelpers.getObjectField(objectField422, k.A);
//                                objectField422 = XposedHelpers.getObjectField(objectField422, k.B);
//                                XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField422});
//                                findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                                XposedBridge.log("h:" + findClass);
//                                newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                                XposedBridge.log("newh:" + newInstance2);
//                                arrayList = new ArrayList();//jujing
//                                b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                                while (b2.moveToNext()) {
//                                    arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                                }
//                                if (arrayList.size() == 0) {
//                                    arrayList.add(Integer.valueOf(2));
//                                }
//                                a2 = b.a("myWechatID", "");
//                                if (a2.equals("")) {
//                                    b = g.b("select value from userinfo where id='2'");
//                                    while (b.moveToNext()) {
//                                        a2 = b.getString(b.getColumnIndex("value"));
//                                    }
//                                }
//                                b2.close();
//                                XposedBridge.log("list_type:" + arrayList);
//                                arrayList2 = new ArrayList();
//                                XposedBridge.log("imagepath:" + arrayList2);
//                                arrayList2.add(str);
//                                a3 = b.a("versionName", "");
//                                XposedBridge.log("version:" + a3);
//                                if (a3.equals("6.5.10") || a3.equals("6.5.16") || a3.equals("6.6.1") || a3.equals("6.6.3") || a3.equals("6.6.5")) {
//                                    XposedBridge.log("version111:" + a3);
//                                    XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                                    XposedBridge.log("发送图片成功6.5.10");
//                                } else {
//                                    XposedBridge.log("发送图片成功6.5.4" + XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true)}));
//                                }
//                                XposedBridge.log("发送图片成功");
//                                b.b("imgSwitch", "true");
//                                i = i2;
//                            }
//                        }
//                    }
//                    XposedHelpers.callMethod(newInstance, k.m, new Object[]{Integer.valueOf(0), Integer.valueOf(0), str, str2, Boolean.valueOf(true), Integer.valueOf(0x7f0201a3)});
//                    new ArrayList().add(str);
//                    XposedHelpers.callMethod(newInstance, k.n, new Object[]{r3, Boolean.valueOf(true), Integer.valueOf(0), Integer.valueOf(0), str2});
//                    concurrentHashMap = (ConcurrentHashMap) XposedHelpers.getObjectField(newInstance, k.o);
//                    XposedBridge.log("cQk1:" + concurrentHashMap);
//                    values = concurrentHashMap.values();
//                    XposedBridge.log("m_e:" + values);
//                    collection = values;
//                    i = 0;
//                    for (Object objectField4222 : collection) {
//                        if (i == 0) {
//                            i2 = i + 1;
//                            XposedBridge.log("object:" + objectField4222);
//                            XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField4222, k.p)));
//                            XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField4222, k.q)).intValue());
//                            XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField4222, k.r)).intValue());
//                            XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField4222, k.s)).intValue());
//                            findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                            XposedBridge.log("pstring:" + findClass2);
//                            XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                            XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                            XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                            str3 = (String) XposedHelpers.getObjectField(objectField4222, k.x);
//                            XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField4222, k.w)));
//                            XposedBridge.log("cQC:" + str3);
//                            longValue = ((Long) XposedHelpers.getObjectField(objectField4222, k.y)).longValue();
//                            objectField2 = XposedHelpers.getObjectField(objectField4222, k.z);
//                            objectField3 = XposedHelpers.getObjectField(objectField4222, k.A);
//                            objectField4222 = XposedHelpers.getObjectField(objectField4222, k.B);
//                            XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField4222});
//                            findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                            XposedBridge.log("h:" + findClass);
//                            newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                            XposedBridge.log("newh:" + newInstance2);
//                            arrayList = new ArrayList();
//                            b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                            while (b2.moveToNext()) {
//                                arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                            }
//                            if (arrayList.size() == 0) {
//                                arrayList.add(Integer.valueOf(2));
//                            }
//                            a2 = b.a("myWechatID", "");
//                            if (a2.equals("")) {
//                                b = g.b("select value from userinfo where id='2'");
//                                while (b.moveToNext()) {
//                                    a2 = b.getString(b.getColumnIndex("value"));
//                                }
//                            }
//                            b2.close();
//                            XposedBridge.log("list_type:" + arrayList);
//                            arrayList2 = new ArrayList();
//                            XposedBridge.log("imagepath:" + arrayList2);
//                            arrayList2.add(str);
//                            XposedBridge.log("version:" + a3);
//                            if (a3.equals("6.5.10")) {
//                            }
//                            XposedBridge.log("version111:" + a3);
//                            XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                            XposedBridge.log("发送图片成功6.5.10");
//                            XposedBridge.log("发送图片成功");
//                            b.b("imgSwitch", "true");
//                            i = i2;
//                        }
//                    }
//                }
//            } catch (Exception e4) {
//                e = e4;
//                e.printStackTrace();
//                concurrentHashMap = (ConcurrentHashMap) XposedHelpers.getObjectField(newInstance, k.o);
//                XposedBridge.log("cQk1:" + concurrentHashMap);
//                values = concurrentHashMap.values();
//                XposedBridge.log("m_e:" + values);
//                collection = values;
//                i = 0;
//                for (Object objectField42222 : collection) {
//                    if (i == 0) {
//                        i2 = i + 1;
//                        XposedBridge.log("object:" + objectField42222);
//                        XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField42222, k.p)));
//                        XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField42222, k.q)).intValue());
//                        XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField42222, k.r)).intValue());
//                        XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField42222, k.s)).intValue());
//                        findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                        XposedBridge.log("pstring:" + findClass2);
//                        XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                        XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                        XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                        str3 = (String) XposedHelpers.getObjectField(objectField42222, k.x);
//                        XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField42222, k.w)));
//                        XposedBridge.log("cQC:" + str3);
//                        longValue = ((Long) XposedHelpers.getObjectField(objectField42222, k.y)).longValue();
//                        objectField2 = XposedHelpers.getObjectField(objectField42222, k.z);
//                        objectField3 = XposedHelpers.getObjectField(objectField42222, k.A);
//                        objectField42222 = XposedHelpers.getObjectField(objectField42222, k.B);
//                        XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField42222});
//                        findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                        XposedBridge.log("h:" + findClass);
//                        newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                        XposedBridge.log("newh:" + newInstance2);
//                        arrayList = new ArrayList();
//                        b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                        while (b2.moveToNext()) {
//                            arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                        }
//                        if (arrayList.size() == 0) {
//                            arrayList.add(Integer.valueOf(2));
//                        }
//                        a2 = b.a("myWechatID", "");
//                        if (a2.equals("")) {
//                            b = g.b("select value from userinfo where id='2'");
//                            while (b.moveToNext()) {
//                                a2 = b.getString(b.getColumnIndex("value"));
//                            }
//                        }
//                        b2.close();
//                        XposedBridge.log("list_type:" + arrayList);
//                        arrayList2 = new ArrayList();
//                        XposedBridge.log("imagepath:" + arrayList2);
//                        arrayList2.add(str);
//                        a3 = b.a("versionName", "");
//                        XposedBridge.log("version:" + a3);
//                        if (a3.equals("6.5.10")) {
//                        }
//                        XposedBridge.log("version111:" + a3);
//                        XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                        XposedBridge.log("发送图片成功6.5.10");
//                        XposedBridge.log("发送图片成功");
//                        b.b("imgSwitch", "true");
//                        i = i2;
//                    }
//                }
//            }
//        } catch (Exception e322) {
//            exception = e322;
//            newInstance = null;
//            e = exception;
//            e.printStackTrace();
//            concurrentHashMap = (ConcurrentHashMap) XposedHelpers.getObjectField(newInstance, k.o);
//            XposedBridge.log("cQk1:" + concurrentHashMap);
//            values = concurrentHashMap.values();
//            XposedBridge.log("m_e:" + values);
//            collection = values;
//            i = 0;
//            for (Object objectField422222 : collection) {
//                if (i == 0) {
//                    i2 = i + 1;
//                    XposedBridge.log("object:" + objectField422222);
//                    XposedBridge.log("cQA:" + ((String) XposedHelpers.getObjectField(objectField422222, k.p)));
//                    XposedBridge.log("cNS:" + ((Integer) XposedHelpers.getObjectField(objectField422222, k.q)).intValue());
//                    XposedBridge.log("bcs:" + ((Integer) XposedHelpers.getObjectField(objectField422222, k.r)).intValue());
//                    XposedBridge.log("bhG:" + ((Integer) XposedHelpers.getObjectField(objectField422222, k.s)).intValue());
//                    findClass2 = XposedHelpers.findClass(k.t, y.e.classLoader);
//                    XposedBridge.log("pstring:" + findClass2);
//                    XposedBridge.log("pstringNew:" + XposedHelpers.newInstance(findClass2, new Object[0]));
//                    XposedBridge.log("pInt1New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.u, y.e.classLoader), new Object[0]));
//                    XposedBridge.log("pInt2New:" + XposedHelpers.newInstance(XposedHelpers.findClass(k.v, y.e.classLoader), new Object[0]));
//                    str3 = (String) XposedHelpers.getObjectField(objectField422222, k.x);
//                    XposedBridge.log("cQB:" + ((String) XposedHelpers.getObjectField(objectField422222, k.w)));
//                    XposedBridge.log("cQC:" + str3);
//                    longValue = ((Long) XposedHelpers.getObjectField(objectField422222, k.y)).longValue();
//                    objectField2 = XposedHelpers.getObjectField(objectField422222, k.z);
//                    objectField3 = XposedHelpers.getObjectField(objectField422222, k.A);
//                    objectField422222 = XposedHelpers.getObjectField(objectField422222, k.B);
//                    XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(k.C, y.e.classLoader), k.D, new Object[0]), k.E, new Object[]{a2, Integer.valueOf(r10), Integer.valueOf(r11), Integer.valueOf(r12), r13, r14, r15, a, str3, Long.valueOf(longValue), objectField2, objectField3, objectField422222});
//                    findClass = XposedHelpers.findClass(k.F, y.e.classLoader);
//                    XposedBridge.log("h:" + findClass);
//                    newInstance2 = XposedHelpers.newInstance(findClass, new Object[0]);
//                    XposedBridge.log("newh:" + newInstance2);
//                    arrayList = new ArrayList();
//                    b2 = g.b("select id from ImgInfo2 order by id desc limit 0,1 ");
//                    while (b2.moveToNext()) {
//                        arrayList.add(Integer.valueOf(b2.getString(b2.getColumnIndex("id"))));
//                    }
//                    if (arrayList.size() == 0) {
//                        arrayList.add(Integer.valueOf(2));
//                    }
//                    a2 = b.a("myWechatID", "");
//                    if (a2.equals("")) {
//                        b = g.b("select value from userinfo where id='2'");
//                        while (b.moveToNext()) {
//                            a2 = b.getString(b.getColumnIndex("value"));
//                        }
//                    }
//                    b2.close();
//                    XposedBridge.log("list_type:" + arrayList);
//                    arrayList2 = new ArrayList();
//                    XposedBridge.log("imagepath:" + arrayList2);
//                    arrayList2.add(str);
//                    a3 = b.a("versionName", "");
//                    XposedBridge.log("version:" + a3);
//                    if (a3.equals("6.5.10")) {
//                    }
//                    XposedBridge.log("version111:" + a3);
//                    XposedHelpers.callMethod(newInstance2, k.G, new Object[]{arrayList, a2, str2, arrayList2, Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(0x7f0201ae)});
//                    XposedBridge.log("发送图片成功6.5.10");
//                    XposedBridge.log("发送图片成功");
//                    b.b("imgSwitch", "true");
//                    i = i2;
//                }
//            }
//        }
//    }
//
//    public static void a(String str, String str2, int i) {
//        try {
//            a(y.e.classLoader);
//            XposedBridge.log("111111111");
//            Class findClass = XposedHelpers.findClass(y.a.f, y.e.classLoader);
//            XposedBridge.log("netscenequeueclass:" + findClass);
//            if (findClass != null) {
//                Object staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(y.a.f, y.e.classLoader), y.a.g);
//                XposedBridge.log("发送消息:" + staticObjectField);
//                if (staticObjectField != null) {
//                    String str3 = y.a.h;
//                    Object[] objArr = new Object[1];
//                    objArr[0] = XposedHelpers.newInstance(b, new Object[]{str, str2, Integer.valueOf(i)});
//                    XposedHelpers.callMethod(staticObjectField, str3, objArr);
//                    XposedBridge.log("发送消息成功");
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    public static void a(String str, List list) {
//        try {
//            Object newInstance = XposedHelpers.findClass(d.a, y.e.classLoader).getDeclaredConstructor(new Class[]{String.class, List.class, Integer.TYPE}).newInstance(new Object[]{str, list, Integer.valueOf(0)});
//            Object callStaticMethod = XposedHelpers.callStaticMethod(XposedHelpers.findClass(d.b, y.e.classLoader), d.c, new Object[0]);
//            XposedBridge.log("objm" + callStaticMethod);
//            XposedHelpers.callMethod(callStaticMethod, d.d, new Object[]{newInstance});
//            XposedHelpers.callMethod(callStaticMethod, d.e, new Object[]{newInstance, Integer.valueOf(0)});
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e2) {
//            e2.printStackTrace();
//        } catch (InvocationTargetException e3) {
//            e3.printStackTrace();
//        } catch (NoSuchMethodException e4) {
//            e4.printStackTrace();
//        }
//        XposedBridge.log("踢人成功");
//    }
//
//    private static String b() {
//        long j = 0;
//        File[] listFiles = new File("/storage/emulated/0/tencent/MicroMsg").listFiles();
//        int i = 0;
//        int i2 = -1;
//        while (i < listFiles.length) {
//            if (listFiles[i].isDirectory() && listFiles[i].getName().length() == 32) {
//                long lastModified = listFiles[i].lastModified();
//                if (j < lastModified) {
//                    i2 = i;
//                    j = lastModified;
//                }
//            }
//            i++;
//        }
//        return i2 != -1 ? listFiles[i2].getName() : "";
//    }
//
//    public static void b(String str, String str2) {
//        try {
//            int intValue = Integer.valueOf(d(str, String.valueOf(System.currentTimeMillis()))).intValue();
//            int i = intValue / 1000;
//            if ((intValue % 1000) / 100 >= 5) {
//                i++;
//            }
//            b.b("duration:" + intValue);
//            Class findClass = XposedHelpers.findClass(k.N, y.e.classLoader);
//            b.b("p:" + findClass);
//            XposedHelpers.callStaticMethod(findClass, k.O, new Object[]{r1, Integer.valueOf(i), str2, str, Integer.valueOf(0), "", Integer.valueOf(43)});
//            b.b("ppppppppppppppppppppppppppp:");
//            Thread.sleep(1000);
//            Class findClass2 = XposedHelpers.findClass(k.P, y.e.classLoader);
//            b.b("d:" + findClass2);
//            Object newInstance = findClass2.getConstructor(new Class[]{String.class}).newInstance(new Object[]{r1});
//            b.b("dNew:" + newInstance);
//            Thread.sleep(1000);
//            XposedHelpers.callMethod(newInstance, k.Q, new Object[0]);
//            b.b("成功了 boo:");
//            b.b("videoSwitch", "true");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e2) {
//            e2.printStackTrace();
//        } catch (IllegalAccessException e3) {
//            e3.printStackTrace();
//        } catch (InvocationTargetException e4) {
//            e4.printStackTrace();
//        } catch (NoSuchMethodException e5) {
//            e5.printStackTrace();
//        }
//    }
//
//    public static void c(String str, String str2) {
//        Object newInstance;
//        InstantiationException e;
//        Object staticObjectField;
//        IllegalAccessException e2;
//        InvocationTargetException e3;
//        NoSuchMethodException e4;
//        Class findClass = XposedHelpers.findClass(k.T, y.e.classLoader);
//        b.b("modelvoice_f:" + findClass);
//        Class findClass2 = XposedHelpers.findClass(k.U, y.e.classLoader);
//        b.b("modelvoice_q:" + findClass2);
//        b.b("str4:" + ((String) XposedHelpers.callStaticMethod(findClass2, k.V, new Object[]{str2})));
//        String str3 = (String) XposedHelpers.callStaticMethod(findClass2, k.W, new Object[]{r0, Boolean.valueOf(false)});
//        b.b("str6:" + str3);
//        b.d(str, str3);
//        b.c("time", ((float) new File(str).length()) + "");
//        b.b("boo:" + ((Boolean) XposedHelpers.callStaticMethod(findClass2, k.X, new Object[]{r0, Integer.valueOf(((int) r1) / 2), Integer.valueOf(0)})).booleanValue());
//        try {
//            newInstance = findClass.getDeclaredConstructor(new Class[]{String.class}).newInstance(new Object[]{r0});
//            try {
//                b.b("localObject2:" + newInstance);
//            } catch (InstantiationException e5) {
//                e = e5;
//                b.b("localObject211111111:" + newInstance);
//                e.printStackTrace();
//                staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//                b.b("nNew:" + staticObjectField);
//                XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//                b.b("发送语音成功");
//                b.b("voiceswitch", "true");
//            } catch (IllegalAccessException e6) {
//                e2 = e6;
//                b.b("localObject222222222222:" + newInstance);
//                e2.printStackTrace();
//                staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//                b.b("nNew:" + staticObjectField);
//                XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//                b.b("发送语音成功");
//                b.b("voiceswitch", "true");
//            } catch (InvocationTargetException e7) {
//                e3 = e7;
//                b.b("localObject233333333333333333:" + e3.getMessage());
//                e3.printStackTrace();
//                staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//                b.b("nNew:" + staticObjectField);
//                XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//                b.b("发送语音成功");
//                b.b("voiceswitch", "true");
//            } catch (NoSuchMethodException e8) {
//                e4 = e8;
//                b.b("localObject244444444444444444:" + e4.getMessage());
//                e4.printStackTrace();
//                staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//                b.b("nNew:" + staticObjectField);
//                XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//                b.b("发送语音成功");
//                b.b("voiceswitch", "true");
//            }
//        } catch (InstantiationException e9) {
//            InstantiationException instantiationException = e9;
//            newInstance = null;
//            e = instantiationException;
//            b.b("localObject211111111:" + newInstance);
//            e.printStackTrace();
//            staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//            b.b("nNew:" + staticObjectField);
//            XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//            b.b("发送语音成功");
//            b.b("voiceswitch", "true");
//        } catch (IllegalAccessException e10) {
//            IllegalAccessException illegalAccessException = e10;
//            newInstance = null;
//            e2 = illegalAccessException;
//            b.b("localObject222222222222:" + newInstance);
//            e2.printStackTrace();
//            staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//            b.b("nNew:" + staticObjectField);
//            XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//            b.b("发送语音成功");
//            b.b("voiceswitch", "true");
//        } catch (InvocationTargetException e11) {
//            InvocationTargetException invocationTargetException = e11;
//            newInstance = null;
//            e3 = invocationTargetException;
//            b.b("localObject233333333333333333:" + e3.getMessage());
//            e3.printStackTrace();
//            staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//            b.b("nNew:" + staticObjectField);
//            XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//            b.b("发送语音成功");
//            b.b("voiceswitch", "true");
//        } catch (NoSuchMethodException e12) {
//            NoSuchMethodException noSuchMethodException = e12;
//            newInstance = null;
//            e4 = noSuchMethodException;
//            b.b("localObject244444444444444444:" + e4.getMessage());
//            e4.printStackTrace();
//            staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//            b.b("nNew:" + staticObjectField);
//            XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//            b.b("发送语音成功");
//            b.b("voiceswitch", "true");
//        }
//        staticObjectField = XposedHelpers.getStaticObjectField(XposedHelpers.findClass(k.Y, y.e.classLoader), k.Z);
//        b.b("nNew:" + staticObjectField);
//        XposedHelpers.callMethod(staticObjectField, k.aa, new Object[]{newInstance});
//        b.b("发送语音成功");
//        b.b("voiceswitch", "true");
//    }
//
//    private static String d(String str, String str2) {
//        String b = b();
//        String str3 = "/storage/emulated/0/tencent/MicroMsg/" + b + "/video/" + str2 + ".mp4";
//        b = "/storage/emulated/0/tencent/MicroMsg/" + b + "/video/" + str2 + ".jpg";
//        b.d(str, str3);
//        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
//        mediaMetadataRetriever.setDataSource(new File(str3).getAbsolutePath());
//        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
//        String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
//        try {
//            Bitmap createBitmap = Bitmap.createBitmap(frameAtTime.getWidth(), frameAtTime.getHeight(), Config.ARGB_8888);
//            Canvas canvas = new Canvas(createBitmap);
//            canvas.drawColor(-1);
//            canvas.drawBitmap(frameAtTime, 0.0f, 0.0f, null);
//            OutputStream fileOutputStream = new FileOutputStream(new File(b));
//            createBitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return extractMetadata;
//    }
//}