package org.tinlone.demo.rxjavasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.util.statusbar.eye.Eyes;
import org.tinlone.demo.rxjavasample.video.NetVideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvInfo)
    TextView mTvInfo;
    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eyes.setStatusBarImage(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button4, R.id.button5})
    public void onClick(View v) {
        mTvInfo.setText("start");
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this,NetVideoActivity.class));
                break;
            case R.id.button2:
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, RecyclerviewActivity.class));
                break;
            default:
                break;
        }
    }

    private void logf(Object obj) {
        Log.i("zjl", " #----- FOCUS -----# " + obj.toString());
    }


}
