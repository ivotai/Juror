package com.unicorn.juror.app

import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun TextView.clicks() = RxView.clicks(this).throttleFirst(1, TimeUnit.SECONDS)

fun <T> Observable<Response<T>>.default(): Observable<ViewState<Response<T>>> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ViewState(response = it) }
            .onErrorReturn { ViewState(error = it) }
            .startWith(ViewState())

}