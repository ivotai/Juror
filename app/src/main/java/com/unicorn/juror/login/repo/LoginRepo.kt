package com.unicorn.juror.login.repo

import com.unicorn.juror.app.ViewState
import com.unicorn.juror.login.api.LoginApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginRepo @Inject constructor(private val api: LoginApi) {

    fun login() = api.login("","")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ViewState(data = it) }
            .onErrorReturn { ViewState(error = it) }
            .startWith(ViewState())
}