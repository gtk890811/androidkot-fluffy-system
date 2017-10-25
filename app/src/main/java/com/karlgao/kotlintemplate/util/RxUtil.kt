package com.karlgao.kotlintemplate.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Extension function for rx
 *
 * Created by Karl on 23/10/17.
 */

fun <T> Observable<T>.rxBuild(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}

// implementation to avoid putting an empty error implementation
fun <T> Observable<T>.subscribeErrorFree(onNext: (t: T) -> Unit): Disposable {
    return this.subscribe(onNext, {})
}
