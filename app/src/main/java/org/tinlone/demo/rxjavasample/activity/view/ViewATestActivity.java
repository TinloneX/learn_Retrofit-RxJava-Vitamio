package org.tinlone.demo.rxjavasample.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.widget.TestView1;

public class ViewATestActivity extends AppCompatActivity {

    private FrameLayout aRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_atest);
        initView();
    }

    private void initView() {
        aRoot = (FrameLayout) findViewById(R.id.a_root);
        aRoot.addView(new TestView1(this));
    }
}
