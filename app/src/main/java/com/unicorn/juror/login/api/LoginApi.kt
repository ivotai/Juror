package com.unicorn.juror.login.api

import io.reactivex.Observable
import retrofit2.http.GET

interface LoginApi {

    @GET("")
    fun login(): Observable<Any>

}