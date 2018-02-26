package org.tinlone.demo.rxjavasample.util.adapt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

import org.tinlone.demo.rxjavasample.util.FileSizeUtil;
import org.tinlone.demo.rxjavasample.util.TLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2018/1/10 0010
 */

public class PhoneInfo {
    public static final String TAG = "phoneInfo";

    public static void info() {
        isRoot();
        TLog.i(TAG,getAvailMemory());
        getCpuInfo();
    }


    /**
     * 获取软件包名,版本名，版本号
     */
    public static void getPackage(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            TLog.i(TAG, "Package:" + pkName + "\nversionName:" + versionName + "\nversionCode:" + versionCode);
        } catch (Exception ignored) {
        }
    }

    /**
     * 获取手机是否root信息
     */
    public static void isRoot() {
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                TLog.i(TAG, "Root:false");
            } else {
                TLog.i(TAG, "Root:true");
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * 获取android当前可用内存大小
     */
    public static String getAvailMemory() {
        // 获取android当前可用内存大小         
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        String fileSize = FileSizeUtil.formatFileSize(blockSize * availableBlocks);
        return "当前可用内存：" + fileSize;
    }

    /**
     * 获取IMEI号，IESI号，手机型号
     */
    @SuppressLint("HardwareIds")
    public static String[] getInfo(final Context context) {
        final String [] result = new String[3];

        PermissionUtil.checkAndRequestPermission(context, Manifest.permission.READ_PHONE_STATE, new PermissionUtil.PermissionCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void allowed() {
                TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = null;
                String imsi = null;
                String number = null;
                if (mTm != null) {
                    result[0] = mTm.getDeviceId();
                    result[1] = mTm.getSubscriberId();
                    result[2] = mTm.getLine1Number();
                }
                TLog.i(TAG, "手机IMEI号：" + imei + ",手机IESI号：" + imsi + ",手机号码:" + number);
            }

            @Override
            public void denied() {

            }
        });
        return result;
    }

    /**
     * 获取手机MAC地址
     * 只有手机开启wifi才能获取到mac地址
     */
    @SuppressLint("HardwareIds")
    public static String getMacAddress(Context context) {
        String result = "";
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            result = wifiInfo.getMacAddress();
        }
        return "手机macAdd:" + result;
    }

    /**
     * 手机CPU信息
     */
    public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2;
        //1-cpu型号 //2-cpu频率  
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException ignored) {
        }
        TLog.i(TAG, "CPU型号:" + cpuInfo[0] + ", CPU频率：" + cpuInfo[1]);
        return cpuInfo;
    }
}
