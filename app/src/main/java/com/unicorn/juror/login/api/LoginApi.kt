package com.unicorn.juror.login.api

import com.unicorn.juror.app.Response
import com.unicorn.juror.login.model.UserInfo
import com.unicorn.juror.registration.model.PersonalInfo
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    @POST("login/userLogin")
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<Response<UserInfo>>

    @POST("login/userUpPassword")
    fun updatePwd(@Query("id") id: String, @Query("username") username: String, @Query("oldpassword") oldPwd: String,
                  @Query("newpassword") newPwd: String): Observable<Response<UserInfo>>

    @POST("app/addPersonMessage")
    fun register(@Query("name") name: String, @Query("xb") sex: String, @Query("jg") nativelyPlace: String, @Query("mzdm") nation: String,
                 @Query("birthday") birthday: String, @Query("sfhm") identifyCard: String, @Query("lxsj") telephone: String, @Query("zz") address: String)
            : Observable<Response<Any>>

    @POST("app/queryPersonMessage")
    fun queryPersonalMessage(@Query("id") id:String,@Query("sfhm") loginName:String):Observable<Response<PersonalInfo>>

}