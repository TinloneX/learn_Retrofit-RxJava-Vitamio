package org.tinlone.demo.rxjavasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.activity.rx.RxListActivity;
import org.tinlone.demo.rxjavasample.util.CountObserver;
import org.tinlone.demo.rxjavasample.util.CountUtil;
import org.tinlone.demo.rxjavasample.util.statusbar.eye.Eyes;
import org.tinlone.demo.rxjavasample.video.NetVideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

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
    private Disposable mCountDown;
    private Disposable mCountUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, NetVideoActivity.class));
                break;
            case R.id.button2:
                countDown();
                break;
            case R.id.button3:
                countUp();
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, ViewListActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, RxListActivity.class));
                break;
            default:
                break;
        }
    }

    private void countUp() {
        CountUtil.countUp(11, new CountObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                mCountUp = d;
            }

            @Override
            public void onNext(Integer t) {
                mButton3.setText(String.format("计时%ss", t));
            }

            @Override
            public void onComplete() {
                mButton3.setText(String.format("计时%s", ""));
            }
        });
    }

    private void countDown() {
        CountUtil.countDown(11, new CountObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                mCountDown = d;
            }

            @Override
            public void onNext(Integer t) {
                mButton2.setText(String.format("倒计时%ss", t - 1 < 0 ? 0 : t - 1));
            }

            @Override
            public void onComplete() {
                mButton2.setText(String.format("倒计时%s", ""));
            }
        });
    }

    private void logf(Object obj) {
        Log.i("zjl", " #----- FOCUS -----# " + obj.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDown != null) {
            mCountDown.dispose();
            mCountDown = null;
        }
        if (mCountUp != null) {
            mCountUp.dispose();
            mCountUp = null;
        }
    }
}
