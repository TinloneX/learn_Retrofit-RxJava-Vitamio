package org.tinlone.demo.rxjavasample.util.adapt;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import org.tinlone.demo.rxjavasample.util.TLog;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    public static boolean judgePostCallPermission(Context context) {
//         判断权限
        boolean callRecordPermission = (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG));
        boolean callPermission = (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.CALL_PHONE));
        boolean contactsPermission = (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.READ_CONTACTS));
        return contactsPermission && callRecordPermission && callPermission;
    }

    public static boolean hasMessagePermission(Context context) {
//         判断权限
        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.SEND_SMS));
    }

    /**
     * this method just used test
     */
    public static boolean hasCallPermission(Context context) {
        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.CALL_PHONE));
    }

    public static boolean hasReadFilePermission(Context context) {

        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    public static boolean hasWriteFilePermission(Context context) {

        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    public static boolean hasRecordPermission(Context context) {

        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO));
    }

    public static boolean hasCameraPermission(Context context) {

        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.CAMERA));
    }

    public static boolean hasPermission(Context context, String permission) {
        return (PackageManager.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, permission));
    }

    public static boolean hasCallRecordPermission(Context context) {
        return (PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) && PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    /**
     * {@link Manifest.permission}
     *
     * @param request     REQUEST_CODE
     * @param permissions Manifest.permission.CAMERA
     */
    public static int checkAndRequestPermission(int request, final Context context, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) return Integer.MIN_VALUE;
//        int requestCode = new Random().nextInt(100) + permissions.length;
        ArrayList<String> noPermission = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (!hasPermission(context, permissions[i])) {
                noPermission.add(permissions[i]);
                Log.e("zjl", "没有权限---" + permissions[i]);
            } else {
                Log.e("zjl", "有权限---" + permissions[i]);
            }
        }
        int size = noPermission.size();

        if (size > 0) {
            String[] no = new String[size];
            for (int j = 0; j < size; j++) {
                no[j] = noPermission.get(j);
            }
            ActivityCompat.requestPermissions((Activity) context,
                    no,
                    request);
        } else {
            Log.d("", "权限数组中的权限都有" + permissions.length);
            return request;
        }

        return request;
    }

    /**
     * 请求权限
     *
     * @param context    界面对象
     * @param permission 权限对象
     * @param callback   权限状态回调
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void checkAndRequestPermission(@NonNull final Context context, @NonNull String permission, @NonNull PermissionCallback callback) {
        if (hasPermission(context, permission)) {
            TLog.f("有权限：" + permission);
            callback.allowed();
        } else {
            TLog.f("没有权限获取权限--" + permission);
            askPermission(context, permission, callback);
        }
    }

    /**
     * 引导至设置页面
     */
    public static void whenEverDenied(@NonNull Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri1 = Uri.parse("package:" + BaseAdapt.PACKAGE_NAME);
        intent.setData(uri1);
        context.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void askPermission(@NonNull final Context context, @NonNull final String permission, @NonNull final PermissionCallback callback) {
        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(permission).build(), new AcpListener() {
            @Override
            public void onGranted() {
                if (PhoneBrand.isXiaoMi()) {
                    XiaoMiAdapt.checkOpsPermission(context, permission, new XiaoMiAdapt.XiaoMiPermissionCallback() {
                        @Override
                        public void allowed() {
                            callback.allowed();
                        }

                        @Override
                        public void ignored() {
                            callback.denied();
                        }
                    });
                } else {
                    callback.allowed();
                }
            }

            @Override
            public void onDenied(List<String> permissions) {
                callback.denied();
            }
        });
    }

    /**
     * 权限状态回调
     */
    public interface PermissionCallback {
        /**
         * 权限可用
         */
        void allowed();

        /**
         * 权限不可用
         */
        void denied();
    }
}


