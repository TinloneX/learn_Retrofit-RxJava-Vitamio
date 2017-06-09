package org.tinlone.demo.rxjavasample.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import de.mateware.snacky.Snacky;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class Tips {
    private static Toast sToast;
    private static boolean DEBUG = true;

    public static void toast(Context context, Object message) {
        if (message == null) return;
        if (sToast == null) {
            sToast = Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message.toString());
        }
    }

    public static void snackySuccess(Activity activity, String info) {
        Snacky.builder()
                .setActivty(activity)
                .setText("" + info)
                .setDuration(Snacky.LENGTH_SHORT)
                .success()
                .show();
    }

    public static void snackyWarning(Activity activity, String info) {
        Snacky.builder()
                .setActivty(activity)
                .setText("" + info)
                .setDuration(Snacky.LENGTH_SHORT)
                .warning()
                .show();
    }

    public static void snackyError(Activity activity, String info) {
        Snacky.builder()
                .setActivty(activity)
                .setText("" + info)
                .setDuration(Snacky.LENGTH_SHORT)
                .error()
                .show();
    }

    public static void snackyInfo(Activity activity, String info) {
        Snacky.builder()
                .setActivty(activity)
                .setText("" + info)
                .setDuration(Snacky.LENGTH_SHORT)
                .info()
                .show();
    }

    public static void snackyTest(Activity activity, Object info) {
        if (DEBUG) {
            snackyInfo(activity, info.toString());
        }
    }
}
