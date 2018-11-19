package org.tinlone.demo.rxjavasample.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.widget.CanvasChangeView;
import org.tinlone.demo.rxjavasample.widget.PathView;
import org.tinlone.demo.rxjavasample.widget.RegionView;
import org.tinlone.demo.rxjavasample.widget.TestView1;

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
            aRoot.removeAllViews();
            switch (params.getInt("index", 0)) {
                case 0:
                    aRoot.addView(new TestView1(this));
                    break;
                case 1:
                    aRoot.addView(new PathView(this));
                    break;
                case 2:
                    aRoot.addView(new RegionView(this));
                    break;
                case 3:
                    doCanvas();
                    break;
                default:
                    break;
            }
        }
    }

    private void doCanvas() {
        final boolean[] flag = {false};
        CanvasChangeView view = new CanvasChangeView(this);
        view.setOnClickListener(v -> view.translate(flag[0] = !flag[0])
                .rotate(flag[0])
                .scale(flag[0])
                .skew(flag[0])
                .draw1());
        aRoot.addView(view);
    }
}
