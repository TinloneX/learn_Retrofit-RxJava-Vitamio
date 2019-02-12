package org.tinlone.demo.rxjavasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.activity.rx.RxListActivity;
import org.tinlone.demo.rxjavasample.activity.view.ViewListActivity;
import org.tinlone.demo.rxjavasample.util.CountObserver;
import org.tinlone.demo.rxjavasample.util.CountUtil;
import org.tinlone.demo.rxjavasample.video.JieCaoActivity;
import org.tinlone.demo.rxjavasample.video.NetVideoActivity;
import org.tinlone.demo.rxjavasample.widget.TMessageDialog;

import java.util.Observer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    private Disposable mCountUp;
    private Disposable subscribe;
    private TMessageDialog tMessageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTvInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                initMessageDialog();
                tMessageDialog.show();
                break;
            case R.id.button2:
                multiAsync();
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

    private void initMessageDialog() {
        tMessageDialog = new TMessageDialog(this)
                .withoutMid()
                .title("选择播放器播放视频")
                .left("Vitamio")
                .right("JieCao")
                .doClick(new TMessageDialog.DoClickListener() {
                    @Override
                    public void onClickLeft(View view) {
                        startActivity(new Intent(MainActivity.this, NetVideoActivity.class));
                        tMessageDialog.dismiss();
                    }

                    @Override
                    public void onClickRight(View view) {
                        startActivity(new Intent(MainActivity.this, JieCaoActivity.class));
                        tMessageDialog.dismiss();
                    }
                });
    }

    Function<Integer, ObservableSource<Boolean>> doingOne = o ->
            Observable.just(getExternalCacheDir() != null).delay(2, TimeUnit.SECONDS);

    Function<Boolean, ObservableSource<String>> doingTwo = o ->
            Observable.just(String.format("\n上传%s\n 返回图片路径%s\n", o ? "成功" : "失败",
                    Environment
                            .isExternalStorageEmulated()
                            ? Environment.getExternalStorageDirectory().getAbsolutePath()
                            : "获取路径失败"))
                    .delay(1, TimeUnit.SECONDS);


    private void multiAsync() {
        mTvInfo.append("\n 模拟上传图片");
        subscribe = Observable.just(1)
                .subscribeOn(Schedulers.newThread())
                .flatMap(doingOne)
                .flatMap(doingTwo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mTvInfo.append(s));
    }


    private void countUp() {
        CountUtil.countUp(101, new CountObserver() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose(mCountUp, subscribe);
    }

    private void dispose(Disposable... disposables) {
        for (Disposable i : disposables) {
            if (i != null) {
                i.dispose();
            }
        }
    }
}
