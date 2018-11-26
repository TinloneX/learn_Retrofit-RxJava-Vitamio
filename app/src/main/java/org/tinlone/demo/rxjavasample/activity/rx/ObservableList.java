package org.tinlone.demo.rxjavasample.activity.rx;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.tinlone.demo.rxjavasample.util.TLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ObservableList {
    private static final String DEMO_STRING = "Hello,World!";
    private static final String DEMO_STRING_LONG = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final ArrayList<CharSequence> DATA_SRC_1 = new ArrayList<CharSequence>() {{
        char[] chars = DEMO_STRING_LONG.toCharArray();
        for (char c : chars) {
            add(String.valueOf(c));
        }
    }};

    /**
     * 逐个发送
     */
    private static final Observable<CharSequence> ob2 = Observable.just(DEMO_STRING)
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
    private static final Observable<Long> obt_500 = Observable.interval(0, 500, TimeUnit.MILLISECONDS);
    private static final Observable<Long> obt_1000 = Observable.interval(0, 1, TimeUnit.SECONDS);
    /**
     * 顺序发送
     */
    private static final Observable<CharSequence> obsc = Observable.just(DEMO_STRING)
            .concatMap((Function<CharSequence, ObservableSource<? extends CharSequence>>) s -> {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    list.add(i, String.valueOf(s.charAt(i)));
                }
                return Observable.fromIterable(list);
            });
    /**
     * 顺序发送
     */
    private static final Observable<CharSequence> obsf = Observable.just(DEMO_STRING_LONG)
            .concatMap((Function<CharSequence, ObservableSource<? extends CharSequence>>) s -> {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    list.add(i, String.valueOf(s.charAt(i)));
                }
                return Observable.fromIterable(list);
            })
            .flatMap((Function<CharSequence, ObservableSource<CharSequence>>) charSequence -> {
                long time = 500;
                // 打乱发射次序，模拟可能存在的乱序
                if ("a".equals(String.valueOf(charSequence))) {
                    time = 1000;
                }
                return Observable.just(charSequence).delay(time, TimeUnit.MILLISECONDS);
            });

    /**
     * 顺序发送
     */
    private static final Observable<CharSequence> obscc = Observable.just(DEMO_STRING_LONG)
            .concatMap((Function<CharSequence, ObservableSource<? extends CharSequence>>) s -> {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    list.add(i, String.valueOf(s.charAt(i)));
                }
                return Observable.fromIterable(list);
            })
            .concatMap((Function<CharSequence, ObservableSource<CharSequence>>) charSequence -> {
                long time = 50;
                // 尝试打乱发射次序，观察是否可能存在的乱序
                if ("a".equals(String.valueOf(charSequence))) {
                    time = 100;
                }
                return Observable.just(charSequence).delay(time, TimeUnit.MILLISECONDS);
            });
    /**
     * 逐个定时发送
     */
    private static final Observable<CharSequence> ob3 = Observable.zip(obsc, obt_500, (charSequence, aLong) -> charSequence);
    /**
     * 定时(可能)无序发送
     */
    private static final Observable<CharSequence> ob4 = Observable.zip(obsf, obt_500, (charSequence, aLong) -> charSequence);

    //-------------------------------------创建Observable--------------------------------------------
    /**
     * {@link Iterable<ObservableSource<CharSequence>>}
     */
    private static final ArrayList<Observable<CharSequence>> iterable = new ArrayList<Observable<CharSequence>>() {{
        add(obsf);
        add(obscc);
    }};
    public static final Observable<CharSequence> obd4c = Observable.create(emitter -> {
        for (CharSequence c : DATA_SRC_1) {
            emitter.onNext(c);
        }
        emitter.onComplete();
    });
    /**
     * obsf 和 obscc 抢夺发射权
     */
    private static final Observable<CharSequence> amb = Observable.amb(iterable);

    public static final Observable<CharSequence> obdfi = Observable.fromIterable(DATA_SRC_1);
    @SuppressWarnings("unchecked")
    public static final Observable<CharSequence> obdaa = Observable.ambArray((ObservableSource<CharSequence>) observer -> {
        for (CharSequence c : DATA_SRC_1) {
            observer.onNext(c);
        }
        observer.onComplete();
    });

    public static final Observable<CharSequence> obdcc = Observable.concat(iterable);
    /**
     * 单发
     */
    private static final Observable<String> ob1 = Observable.just(DEMO_STRING);
    /**
     * 至多写10个
     */
    private static final Observable<CharSequence> ob1ja = Observable.just("a", "b", "c", "d", "e");
    /**
     * 自定义{@link Publisher#subscribe(Subscriber)}
     */
    private static final Observable<CharSequence> obfp = Observable.fromPublisher(s -> {
        for (CharSequence c : DATA_SRC_1) {
            s.onNext(c);
        }
        s.onComplete();
    });

    private static final Observable<CharSequence> obg = Observable.generate(stringEmitter -> {
        for (CharSequence c : DATA_SRC_1) {
            stringEmitter.onNext(c);
        }
        stringEmitter.onComplete();
    });


    //-------------------------------------试验各操作符-----------------------------------------------

    private static final Single<Boolean> all = obdfi
            .all(charSequence -> charSequence.charAt(0) > 'a');

    /**
     * 跳过项，直到{@link Observable#interval(long, TimeUnit)}完成
     */
    private static final Observable<CharSequence> skipUntil = obdfi
            .skipUntil(Observable.interval(10, TimeUnit.MILLISECONDS));
    /**
     * 跳过前10项(延时操作)
     */
    private static final Observable<CharSequence> skip = Observable.zip(obdfi
            .skip(10), obt_500, (charSequence, aLong) -> charSequence);
    /**
     * 当项满足条件时跳过
     */
    private static final Observable<CharSequence> skipWhile = obdfi
            .skipWhile(charSequence -> {
                TLog.i(charSequence);
                return charSequence.charAt(0) < 'A';
            });
    /**
     * 取项直到不满足条件
     */
    private static final Observable<CharSequence> takeUtil = obdfi
            .takeWhile(charSequence -> charSequence.charAt(0) < 'A');
    /**
     * 取前10项(延时操作)
     */
    private static final Observable<CharSequence> take = Observable.zip(obdfi
            .take(10), obt_500, (charSequence, aLong) -> charSequence);
    /**
     * 取项知道满足条件
     */
    private static final Observable<CharSequence> takeWhile = obdfi
            .takeUntil(charSequence -> charSequence.charAt(0) >= 'A');

    private static final Observable<CharSequence> concatArray = Observable.concatArray(obdfi, ob1ja, obdcc);

    //----------------------------------------------------------------------------------------------

    /**
     * 列表title
     */
    static final List<String> RX_TITLE = new ArrayList<String>() {{
        add(DEMO_STRING);
        add("concatMap 有序发送");
        add("zip(interval + concatMap) 组合(定时+有序)发送");
        add("zip(interval + flatMap) 组合(定时+无序)发送");
        add("concatMap + delay 有序发送下逻辑时延无效");
        add("abm 选择优先发射");
        add("skipUntil 跳过直到某逻辑完成");
        add("skipWhile 当满足条件跳过");
        add("skip 跳过前n项");
        add("takeUtil 取项直到不满足条件");
        add("takeWhile 当满足条件取");
        add("take 取前n项");
        add("concatArray(obdfi,ob1ja,obdcc) 串接Observable数组");
    }};
    /**
     * 每个操作预计步骤
     */
    static final List<Integer> steps = new ArrayList<Integer>() {{
        add(3);
        add(DEMO_STRING.length() + 2);
        add(DEMO_STRING.length() + 2);
        add(DEMO_STRING_LONG.length() + 2);
        add(DEMO_STRING_LONG.length() + 2);
        add(DEMO_STRING_LONG.length() + 2);
        add(DEMO_STRING_LONG.length() + 2);
        add(DEMO_STRING_LONG.length() + 2 - 10);
        add(DEMO_STRING_LONG.length() + 2 - 10);
        add(10 + 2);
        add(10 + 2);
        add(10 + 2);
        add(DATA_SRC_1.size() + 5 + DEMO_STRING_LONG.length() * 2 + 2);
    }};
    /**
     * 列表操作
     */
    static final ArrayList<Observable> obs = new ArrayList<Observable>() {
        {
            add(ob1);
            add(ob2);
            add(ob3);
            add(ob4);
            add(obscc);
            add(amb);
            add(skipUntil);
            add(skipWhile);
            add(skip);
            add(takeUtil);
            add(takeWhile);
            add(take);
            add(concatArray);
        }
    };


}
