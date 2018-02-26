package org.tinlone.demo.rxjavasample;

import android.app.Application;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class MyApplication extends Application {

    static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
       mApplication = this;
    }

    public static MyApplication getContext() {
        return mApplication;
    }
}
