package org.tinlone.demo.rxjavasample.util;

import android.content.Context;
import android.util.DisplayMetrics;

import org.tinlone.demo.rxjavasample.MyApplication;

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;

        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenWidth() {
        return MyApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenHeight() {
        return MyApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2sp(Context context, float dpValue) {

        return px2sp(context, dip2px(context, dpValue));
    }

    public static float getDensity(Context context) {
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        float density = dm.density;
        int densityDPI = dm.densityDpi;
        return density;
    }

    public static float getDensity() {
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = MyApplication.getContext().getResources().getDisplayMetrics();
        float density = dm.density;
        return density;
    }
}