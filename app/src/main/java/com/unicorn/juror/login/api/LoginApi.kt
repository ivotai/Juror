package com.unicorn.juror.login.api

import com.unicorn.juror.app.Response
import com.unicorn.juror.login.model.UserInfo
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    @POST("login/userLogin")
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<Response<UserInfo>>

    @POST("login/userUpPassword")
    fun updatePassword(@Query("username") username: String, @Query("oldpassword") oldPwd: String, @Query("newpassword") newPwd: String): Observable<Any>


}