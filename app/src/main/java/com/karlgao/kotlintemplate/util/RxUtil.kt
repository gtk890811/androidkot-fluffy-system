package com.karlgao.kotlintemplate.util

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Extension function for rx
 *
 * Created by Karl on 23/10/17.
 */

fun <T> Single<T>.rxBuild(): Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}

// implementation to avoid putting an empty error implementation
fun <T> Single<T>.success(onSuccess: (t: T) -> Unit): Disposable {
    return this.subscribe(onSuccess, {})
}