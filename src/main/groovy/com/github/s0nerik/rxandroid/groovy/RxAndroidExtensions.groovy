package com.github.s0nerik.rxandroid.groovy

import groovy.transform.CompileStatic
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@CompileStatic
class RxAndroidExtensions {
    static <T> Observable<T> applySchedulers(final Observable<T> observable) {
        observable.compose({ Observable<T> it ->
            it.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
        } as Observable.Transformer<T, T>)
    }

    static <T> Observable<T> applySchedulers(final Observable<T> observable, Scheduler subscribeOn, Scheduler observeOn) {
        observable.compose({ Observable<T> it ->
            it.subscribeOn(subscribeOn)
              .observeOn(observeOn)
        } as Observable.Transformer<T, T>)
    }
}
