package org.tinlone.demo.rxjavasample.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.bean.StudentBean;
import org.tinlone.demo.rxjavasample.http.Dao;
import org.tinlone.demo.rxjavasample.util.adapt.PermissionUtil;
import org.tinlone.demo.rxjavasample.util.statusbar.eye.Eyes;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eyes.setStatusBarImage(this);
        ButterKnife.bind(this);
        PermissionUtil.checkAndRequestPermission(this, Manifest.permission.READ_CALL_LOG, new PermissionUtil.PermissionCallback() {
            @Override
            public void allowed() {
//                startService(new Intent(MainActivity.this,CallLogRebuildService.class));
            }

            @Override
            public void denied() {

            }
        });

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button4, R.id.button5})
    public void onClick(View v) {
        mTvInfo.setText("start");
        switch (v.getId()) {
            case R.id.button1:
                demo1();
                break;
            case R.id.button2:
                demo2();
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                break;
            case R.id.button5:
               startActivity(new Intent(MainActivity.this,RecyclerviewActivity.class));
                break;
            default:
                break;
        }
    }

    private void demo2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
                logf("mButton2---");
                mButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logf("     -mButton3---setOnClick");
                        demo3();
                        e.onNext(mButton3.getText().toString());
                    }
                });

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
//                mTvInfo.append("\n" + s);
                mButton3.setOnClickListener(null);
            }
        });

    }

    private void demo3() {
        Dao.getService()
                .getStudent("token", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StudentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        logf("onSubscribe-3--");
                    }

                    @Override
                    public void onNext(@NonNull StudentBean student) {
                        logf("onNext-3--");
                        mTvInfo.append(student.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        logf("onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void demo1() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                logf("subscribe---a");
                emitter.onNext(2);
                logf("subscribe---2");
                emitter.onNext(3);
                logf("subscribe---3");
                emitter.onComplete();
                logf("subscribe---Complete");
                emitter.onNext(4);
                logf("subscribe---4");
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            Disposable dd;

            @Override
            public void onSubscribe(Disposable d) {
                logf("subscribe---" + d.isDisposed());
                dd = d;
            }

            @Override
            public void onNext(Integer value) {
                logf("onNext---" + value);
                mTvInfo.append("\n---" + value);
                if (value == 2) {
                    dd.dispose();
                    logf("onNext---dispose" + value);
                    mTvInfo.append("\n---dispose");
                }
            }

            @Override
            public void onError(Throwable e) {
                logf("onError---");
            }

            @Override
            public void onComplete() {
                logf("complete---");
            }
        };
        //建立连接
        observable.subscribe(observer);

    }

    private void demo4() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                })
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String number) {
                        mTvInfo.append("\n");
                        mTvInfo.append(number);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void logf(Object obj) {
        Log.i("zjl", " #----- FOCUS -----# " + obj.toString());
    }


}
