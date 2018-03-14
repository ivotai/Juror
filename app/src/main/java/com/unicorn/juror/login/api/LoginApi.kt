package com.unicorn.juror.login.api

import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    @POST("login/userLogin")
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<Any>

}