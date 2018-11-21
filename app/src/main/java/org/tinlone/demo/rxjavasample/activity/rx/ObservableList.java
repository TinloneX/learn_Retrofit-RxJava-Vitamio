package org.tinlone.demo.rxjavasample.activity.rx;


import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ObservableList {
    public static final String DEMO_STRING = "Hello,World!";

    /**
     * 列表title
     */
    public static final List<String> RX_TITLE = new ArrayList<String>() {{
        add(DEMO_STRING);
        add("concatMap -> Hello World!");
        add("zip(interval + concatMap) -> Hello World!");
    }};
    /**
     * 每个操作预计步骤
     */
    public static final List<Integer> steps = new ArrayList<Integer>() {{
        add(3);
        add(DEMO_STRING.length() + 2);
        add(DEMO_STRING.length() + 2);
    }};
    /**
     * 单发
     */
    public static final Observable<String> ob1 = Observable.just(DEMO_STRING);
    /**
     * 逐个发送
     */
    public static final Observable<CharSequence> ob2 = Observable.just(DEMO_STRING)
            .concatMap((Function<CharSequence, ObservableSource<? extends CharSequence>>) s -> {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    list.add(i, String.valueOf(s.charAt(i)));
                }
                return Observable.fromIterable(list)
                        .delay(500, TimeUnit.MILLISECONDS);
            });
    /**
     * 定时器
     */
    public static final Observable<Long> obt = Observable.interval(0, 500, TimeUnit.MILLISECONDS);
    /**
     * 顺序发送
     */
    public static final Observable<CharSequence> obst = Observable.just(DEMO_STRING)
            .concatMap((Function<CharSequence, ObservableSource<? extends CharSequence>>) s -> {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    list.add(i, String.valueOf(s.charAt(i)));
                }
                return Observable.fromIterable(list);
            });
    /**
     * 逐个定时发送
     */
    public static final Observable<CharSequence> ob3 = Observable.zip(obst, obt, (charSequence, aLong) -> charSequence);

    /**
     * 列表操作
     */
    public static final SparseArray<Observable> obs = new SparseArray<Observable>() {
        {
            put(0, ob1);
            put(1, ob2);
            put(2, ob3);
        }
    };


}
