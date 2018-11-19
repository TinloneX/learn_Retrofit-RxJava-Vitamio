package org.tinlone.demo.rxjavasample.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import org.tinlone.demo.rxjavasample.util.adapt.PhoneBrand;
import org.tinlone.demo.rxjavasample.util.adapt.PhoneInfo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * @author zhaojinlong
 * 2016/12/20 0020
 */

public class TLog {

    private static final String TAG = "zjl";
    private static boolean showLog = true;

    private TLog() {
    }

    public static void d(Object object) {
        if (showLog) {
            Log.d(TAG, valueOf(object));
        }
    }

    public static void d(String tag, Object object) {
        if (showLog) {
            Log.d(tag, valueOf(object));
        }
    }

    public static void i(Object object) {
        if (showLog) {
            Log.i(TAG, valueOf(object));
        }
    }

    public static void i(String tag, Object object) {
        if (showLog) {
            Log.i(tag, valueOf(object));
        }
    }

    public static void i(String msg, Object... objs) {
        if (showLog) {
            Log.i(TAG, valueOf(msg, objs));
        }
    }

    public static void i(String tag, String msg, Object... objs) {
        if (showLog) {
            Log.i(tag, valueOf(msg, objs));
        }
    }

    public static void e(Object object) {
        if (showLog) {
            Log.e(TAG, valueOf(object));
        }
    }

    public static void e(String msg, Object... objs) {
        if (showLog) {
            Log.e(TAG, valueOf(msg, objs));
        }
    }

    public static void e(String tag, Object object) {
        if (showLog) {
            Log.e(tag, valueOf(object));
        }
    }

    public static void w(Object object) {
        if (showLog) {
            Log.w(TAG, valueOf(object));
        }
    }

    public static void w(String tag, Object object) {
        if (showLog) {
            Log.w(tag, valueOf(object));
        }
    }

    public static void f(Object object) {
        if (showLog) {
            Log.i(TAG, "#- LEVEL_D -#" + valueOf(object));
        }
    }

    public static void f(String tag, Object object) {
        if (showLog) {
            Log.i(tag, "#-----FOCUS-HERE-----#" + valueOf(object));
        }
    }

    public static void th(Throwable object) {
        if (showLog) {
            Log.e(TAG, "这里有异常！！", object);
        }
    }

    public static String valueOf() {
        return "";
    }

    public static String valueOf(Object object) {
        if (object instanceof Throwable) {
            return getStackTraceString((Throwable) object);
        } else if (object instanceof Collection) {
            return String.valueOf("集合的长度：" + object == null ? "null" : ((Collection) object).size());
        }
        return object == null ? "null" : object.toString();
    }

    public static String valueOf(String msg, Object... objs) {
        try {
            return String.format(msg, objs);
        } catch (Exception e) {
            e.printStackTrace();
            return "---WARNING--- 占位符对应格式有误:" + msg;
        }
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    private static void heapSize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = 0;
        if (manager != null) {
            heapSize = manager.getMemoryClass();
        }
        info(valueOf("系统分配内存大小为：" + heapSize + " M "));
        info(valueOf("This app heap-size = " + heapSize + " M "));
    }

    public static void phoneInfo(Context context) {
        try {
            PhoneBrand.showPhoneModel();
            heapSize(context);
            info(valueOf("手机安卓SDK版本: %d", Build.VERSION.SDK_INT));
            info(valueOf("手机屏幕DPI : %s", DensityUtil.getDensity()));
            info(valueOf("手机屏幕 宽 * 高 : %s * %s", DensityUtil.getScreenWidth(), DensityUtil.getScreenHeight()));
            PhoneInfo.info();
        } catch (Exception e) {
            info("打印信息失败");
        }
    }

    public static void info(Object object) {
        if (showLog) {
            Log.i("phoneInfo", valueOf(object));
        }
    }

}
