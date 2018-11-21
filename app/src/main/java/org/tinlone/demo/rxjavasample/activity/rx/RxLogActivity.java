package org.tinlone.demo.rxjavasample.activity.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.util.TLog;

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
        mFab = findViewById(R.id.fab);
        position = getIntent().getIntExtra("position", DEFAULT_POSITION);
        mFab.setMax(ObservableList.steps.get(position));
        doObservable();
        mFab.setOnClickListener(v -> {
            mFab.setProgress(0, true);
            if (mDisposable != null) {
                mDisposable.dispose();
            }
            doObservable();
        });
    }

    @SuppressWarnings("unchecked")
    private void doObservable() {
        ObservableList.obs.get(position)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    int index = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        update(index, String.format("step%s:onSubscribe", index));
                        index++;
                    }

                    @Override
                    public void onNext(Object o) {
                        update(index, o);
                        index++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        update(index, e);
                        index++;
                    }

                    @Override
                    public void onComplete() {
                        update(index, "onComplete");
                        index++;
                    }
                });
    }

    private void update(int index, Object info) {
        mFab.post(() -> {
            mTvInfo.append(String.format("step%s:%s \n", index, TLog.valueOf(info)));
            mFab.setProgress(index + 1, index != 0);
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
