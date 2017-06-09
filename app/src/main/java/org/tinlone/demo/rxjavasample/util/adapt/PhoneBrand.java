package org.tinlone.demo.rxjavasample.util.adapt;

import android.os.Build;

import org.tinlone.demo.rxjavasample.util.TLog;


/**
 * 机型判断类
 * Created by Tinlone on 2017/4/17 0017.
 */
public class PhoneBrand {
    private static final String PACKAGE_NAME = BaseAdapt.PACKAGE_NAME;
    private static final String XIAO_MI = "Xiaomi";
    private static final String HUA_WEI = "HUAWEI";
    private static final String MEI_ZU = "Meizu";
    private static final String VIVO = "vivo";
    private static final String SAMSUNG = "samsung";

    public static void showPhoneModel() {
        TLog.f("-----------FOCUS-----------当前包名：" + PACKAGE_NAME);
        TLog.f("-----------FOCUS-----------当前手机的制造商：" + Build.MANUFACTURER);
    }

    public static boolean isXiaoMi() {
        return Build.MANUFACTURER.equals(XIAO_MI);
    }

    public static boolean isHuaWei() {
        return Build.MANUFACTURER.equals(HUA_WEI);
    }

    public static boolean isMeiZu() {
        return Build.MANUFACTURER.equals(MEI_ZU);
    }

    public static boolean isSamSung() {
        return Build.MANUFACTURER.equals(SAMSUNG);
    }

    /**
     * 获取手机品牌
     *
     * @return 手机品牌
     */
    private static String getBrand() {
        return Build.MANUFACTURER;
    }
}
