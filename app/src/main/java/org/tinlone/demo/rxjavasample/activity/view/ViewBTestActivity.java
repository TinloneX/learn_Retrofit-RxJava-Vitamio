package org.tinlone.demo.rxjavasample.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.widget.PathView;

public class ViewBTestActivity extends AppCompatActivity {

    private FrameLayout bRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_btest);
        initView();
    }

    private void initView() {
        bRoot = (FrameLayout) findViewById(R.id.b_root);
        bRoot.addView(new PathView(this));
    }
}
