package com.unicorn.juror.login.repo

import com.unicorn.juror.app.ViewState
import com.unicorn.juror.login.api.LoginApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginRepo @Inject constructor(private val api: LoginApi) {

    fun login(username: String, password: String) = api.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ViewState(response = it) }
            .onErrorReturn { ViewState(error = it) }
            .startWith(ViewState())

}