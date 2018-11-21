package org.tinlone.demo.rxjavasample.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CountObserver implements Observer<Integer> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public abstract void onNext(Integer t);

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
