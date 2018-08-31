package com.jujing.assistant.util.javafile;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

@SuppressLint("NewApi")
public class PerformClickUtils {

    private static final String TAG = "PerformClickUtils";

    /**
     * 在当前页面查找文字内容并点击
     *
     * @param text
     */
    public static void findTextAndClick(AccessibilityService accessibilityService, String text) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null
                        && (text.equals(nodeInfo.getText()) || text.equals(nodeInfo.getContentDescription()))) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }






    public static AccessibilityNodeInfo getNode(AccessibilityService service, String id) {
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        Log.e(TAG, "getNode285: " );
        if (nodeInfo == null) {
            Log.e(TAG, "getNode287: " );
            return null;
        }
        List<AccessibilityNodeInfo> nodeList = nodeInfo.findAccessibilityNodeInfosByViewId(id);
        for (AccessibilityNodeInfo accessibilityNodeInfo : nodeList) {
            if (accessibilityNodeInfo != null) {
                return accessibilityNodeInfo;
            }
        }
        Log.e(TAG, "getNode295: " );
        return null;
    }

    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static void findViewIdAndClick2(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        List<AccessibilityNodeInfo> nodeInfoList1 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("取消");
        if (nodeInfoList != null && !nodeInfoList.isEmpty() || nodeInfoList1 != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo1 : nodeInfoList1) {
                if (nodeInfo1.equals("取消")) {
                    break;
                } else {
                    for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {

                        if (nodeInfo != null) {
                            performClick(nodeInfo);
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static void findViewIdAndClick1(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    return;
                }
            }
        }
    }

    public static Boolean isHaveID(AccessibilityNodeInfo accessibilityNodeInfo, String id) {
        // TODO Auto-generated method stub
        if (accessibilityNodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    public static String getText(AccessibilityService service, String wightId) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return null;
        }
        List<AccessibilityNodeInfo> wightList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(wightId);
        if (wightList == null || wightId == null) {
            //微信不兼容
            Log.e(TAG, "getText: 微信不兼容");

            return "";
        }
        if (!wightId.isEmpty()) {
            for (AccessibilityNodeInfo wight : wightList) {
                if (wight != null && wight.getText().toString() != null && !wight.getText().toString().isEmpty()) {
                    return wight.getText().toString();
                }
            }
        }
        return "";
    }

    public static String getContentDescription(AccessibilityService service, String wightId) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return null;
        }
        List<AccessibilityNodeInfo> wightList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(wightId);
        if (wightList == null || wightId == null) {
            //微信不兼容
            Log.e(TAG, "getText: 微信不兼容");

            return "";
        }
        if (!wightId.isEmpty()) {
            for (AccessibilityNodeInfo wight : wightList) {
                if (wight != null && wight.getContentDescription().toString() != null && !wight.getContentDescription().toString().isEmpty()) {
                    return wight.getContentDescription().toString();
                }
            }
        }
        return "";
    }

    public static void setText(AccessibilityService service, String id, String text) {
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> nodeInfos = nodeInfo.findAccessibilityNodeInfosByViewId(id);
        for (AccessibilityNodeInfo info : nodeInfos) {
            if (info != null) {
                setText(service, info, text);
            }
        }

    }




    /**
     * 设置文本
     */
    public static void setText(AccessibilityService service, AccessibilityNodeInfo node, String text) {
        node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
        node.performAction(AccessibilityNodeInfo.ACTION_CUT);
        ClipData data = ClipData.newPlainText("reply", text);
        ClipboardManager clipboardManager = (ClipboardManager) service.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(data);
        node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
        node.performAction(AccessibilityNodeInfo.ACTION_PASTE); // 执行粘贴
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 检查viewId进行点击
     *
     * @param
     * @param id
     */
    public static void findViewIdAndClickInfo(AccessibilityNodeInfo accessibilityNodeInfo, String id) {

        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    return;
                }
            }
        }
    }

    public static boolean isHaveID(AccessibilityService accessibilityService, String id) {
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static void findViewIdAndClick3(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }

    /**
     * 在当前页面查找对话框文字内容并点击
     *
     * @param text1 默认点击text1
     * @param text2
     */
    public static void findDialogAndClick(AccessibilityService accessibilityService, String text1, String text2) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> dialogWait = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text1);
        List<AccessibilityNodeInfo> dialogConfirm = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text2);
        if (!dialogWait.isEmpty() && !dialogConfirm.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : dialogWait) {
                if (nodeInfo != null && text1.equals(nodeInfo.getText())) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }

    }

    public static void performLongClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
        } else {
            performLongClick(nodeInfo.getParent());
        }
    }

    //模拟点击事件
    public static void performClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            performClick(nodeInfo.getParent());
        }
    }

    //模拟点击事件
    public static void performFocus(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
        } else {
            performFocus(nodeInfo.getParent());
        }
    }
    public static boolean findViewIdAndFocus(AccessibilityService accessibilityService, String id){
        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performFocus(nodeInfo);
                    break;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static boolean findViewIdAndClick(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public static boolean findViewIdAndLongClick(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performLongClick(nodeInfo);
                    break;
                }
            }
        }else{
            return false;
        }
        return true;
    }




    // 模拟返回事件

    public static void performBack(AccessibilityService service) {
        if (service == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        }
    }
}
