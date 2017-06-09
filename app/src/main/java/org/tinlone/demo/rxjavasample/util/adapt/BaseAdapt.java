package org.tinlone.demo.rxjavasample.util.adapt;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.os.Build;


/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class BaseAdapt {

    protected static final String PACKAGE_NAME = "org.tinlone.demo.rxjavasample";

    @TargetApi(Build.VERSION_CODES.M)
    public static String permission2Ops(String permission) {
        return AppOpsManager.permissionToOp(permission);
    }
}
