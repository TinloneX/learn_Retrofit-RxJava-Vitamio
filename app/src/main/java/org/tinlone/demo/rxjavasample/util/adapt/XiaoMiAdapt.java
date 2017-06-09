package org.tinlone.demo.rxjavasample.util.adapt;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import org.tinlone.demo.rxjavasample.util.TLog;

/**
 * 小米适配工具类
 * Created by Tinlone on 2017/4/17 0017.
 */
public class XiaoMiAdapt extends BaseAdapt {

    private static final String ACTION_ROOT = "miui.intent.action.ROOT_MANAGER";
    private static final String ACTION_PERMISSION = "miui.intent.action.APP_PERM_EDITOR";

    public static void jumpToSetPermission(Context context) {
        TLog.w("----------FOCUS-----------当前包名：" + PACKAGE_NAME);
        if (!PhoneBrand.isXiaoMi()) return;
        Intent intent = new Intent();
        intent.setAction(ACTION_PERMISSION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("extra_pkgname", PACKAGE_NAME);
        context.startActivity(intent);
    }

    /**
     * 检查是否拥有查看通话记录权限（建议在{@link Context#checkSelfPermission(String)}之后执行）
     * 方法内部调用{@link #checkOpsPermission(Context, String, XiaoMiPermissionCallback)}
     *
     * @param context  上下文对象
     * @param callback 响应回调{@link XiaoMiPermissionCallback}
     */
    public static void checkCallLogPermission(Context context, XiaoMiPermissionCallback callback) {
        if (PhoneBrand.isXiaoMi()) {
            checkOpsPermission(context, Manifest.permission.READ_CALL_LOG, callback);
        } else return;
    }

    /**
     * 检查是否拥有权限（建议在{@link Context#checkSelfPermission(String)}之后执行）
     *
     * @param context    上下文对象
     * @param permission AppOpsManager 权限常量字符
     * @param callback   响应回调{@link XiaoMiPermissionCallback}
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void checkOpsPermission(Context context, String permission, XiaoMiPermissionCallback callback) {
        TLog.i("---FOCUS---小米权限---" + permission);
        String ops = permission2Ops(permission);
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int checkOp = appOpsManager.checkOpNoThrow(ops, android.os.Process.myUid(), PACKAGE_NAME);
        if (checkOp == AppOpsManager.MODE_IGNORED) {
            // 权限被拒绝了 .
            callback.ignored();
        } else if (checkOp == AppOpsManager.MODE_ALLOWED) {
            //权限被允许
            callback.allowed();
        }
    }

    /**
     * AppOpsManager响应回调
     */
    public interface XiaoMiPermissionCallback {

        void allowed();

        void ignored();
    }

}
