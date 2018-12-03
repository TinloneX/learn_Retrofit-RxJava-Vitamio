package org.tinlone.demo.rxjavasample.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import org.tinlone.demo.rxjavasample.R;

public class ViewATestActivity extends AppCompatActivity {

    private FrameLayout aRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_atest);
        aRoot = findViewById(R.id.a_root);
        getParams();
    }

    private void getParams() {
        Bundle params = getIntent().getExtras();
        if (params != null) {
            int index = params.getInt("index", 0);
            aRoot.removeAllViews();
            aRoot.addView(ViewListFactory.get(this, index));
        }
    }

    @Override
    protected void onDestroy() {
        aRoot.removeAllViews();
        super.onDestroy();
    }
}
