package org.tinlone.demo.rxjavasample.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.tinlone.demo.rxjavasample.util.TLog;

/**
 * @author Administrator
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                TLog.i("(Main2Activity.java:17) .run->" + TLog.valueOf(8000));
            }
        });
    }
}
