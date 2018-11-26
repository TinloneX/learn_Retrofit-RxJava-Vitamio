package org.tinlone.demo.rxjavasample.activity.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.util.DensityUtil;
import org.tinlone.demo.rxjavasample.util.TLog;
import org.tinlone.demo.rxjavasample.util.Timer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxLogActivity extends AppCompatActivity {
    private static final int DEFAULT_POSITION = 0;
    private TextView mTvInfo;
    private FloatingActionButton mFab;
    private int position;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_log);
        mTvInfo = findViewById(R.id.tv_info);
        ScrollView scrollView = findViewById(R.id.scrollView);
        mFab = findViewById(R.id.fab);
        position = getIntent().getIntExtra("position", DEFAULT_POSITION);
        mTvInfo.setText(ObservableList.RX_TITLE.get(position));
        mFab.setMax(ObservableList.steps.get(position));
        doObservable();
        mFab.setOnClickListener(v -> {
            mFab.setProgress(0, true);
            if (mDisposable != null) {
                if (!mDisposable.isDisposed()){
                    mTvInfo.append(String.format("\n中断执行：%s", Timer.end()));
                    mDisposable.dispose();
                }
            }
            doObservable();
        });
        scrollView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom >= (DensityUtil.getScreenHeight() - 300)) {
                scrollView.post(() -> scrollView.scrollTo(0, bottom-100));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void doObservable() {
        mTvInfo.append(String.format("\n开始执行：%s", Timer.start()));
        ObservableList.obs.get(position)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    int index = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        log(index, "onSubscribe");
                        index++;
                    }

                    @Override
                    public void onNext(Object o) {
                        log(index, o);
                        index++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        log(index, e);
                        index++;
                    }

                    @Override
                    public void onComplete() {
                        log(index, "onComplete");
                        index++;
                    }
                });
    }

    private void log(int index, Object info) {
        mFab.post(() -> {
            mTvInfo.append(String.format("\nstep%s: %s", index, TLog.valueOf(info)));
            mFab.setProgress(index + 1, index != 0);
            if ("onComplete".equals(String.valueOf(info))) {
                mTvInfo.append(String.format("\n结束执行：%s", Timer.end()));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
