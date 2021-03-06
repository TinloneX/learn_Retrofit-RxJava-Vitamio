package org.tinlone.demo.rxjavasample.activity.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.util.DensityUtil;
import org.tinlone.demo.rxjavasample.util.TLog;
import org.tinlone.demo.rxjavasample.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxLogActivity extends AppCompatActivity {
    private static final int DEFAULT_POSITION = 0;

    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fab)
    Button mFab;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private int position;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_log);
        ButterKnife.bind(this);
        position = getIntent().getIntExtra("position", DEFAULT_POSITION);
        tvTitle.setText(ObservableList.RX_TITLE.get(position));
        progressBar.setMax(ObservableList.steps.get(position));
        doObservable();
        scrollView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom >= (DensityUtil.getScreenHeight() - 300)) {
                scrollView.post(() -> scrollView.scrollTo(0, bottom - 100));
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        progressBar.setProgress(0, true);
        if (mDisposable != null) {
            if (!mDisposable.isDisposed()) {
                mTvInfo.append(String.format("\n中断执行：%s", Timer.end()));
                mDisposable.dispose();
            }
        }
        doObservable();
    }

    @SuppressWarnings("unchecked")
    private void doObservable() {
        mTvInfo.append(Html.fromHtml(String.format("<br/><font color='#AF0000'>开始执行：%s</font>", Timer.start())));
        ObservableList.obs.get(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LogObserver());
    }

    private void log(int index, Object info) {
        mTvInfo.append(Html.fromHtml(String.format("<br/><font color='#00BFFF'>step%s:</font> %s", index, TLog.valueOf(info))));
        progressBar.setProgress(index + 1, index != 0);
        if ("onComplete".equals(String.valueOf(info))) {
            mTvInfo.append(Html.fromHtml(String.format("<br/><font color='#AF0000'>结束执行：%s</font>", Timer.end())));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    private class LogObserver implements Observer {
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
    }
}
