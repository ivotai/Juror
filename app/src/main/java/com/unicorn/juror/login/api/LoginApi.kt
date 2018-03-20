package com.unicorn.juror.login.api

import com.unicorn.juror.app.Page
import com.unicorn.juror.app.Response
import com.unicorn.juror.courtTrend.CourtTrend
import com.unicorn.juror.education.court.Court
import com.unicorn.juror.education.model.Material
import com.unicorn.juror.login.model.UserInfo
import com.unicorn.juror.registration.model.PersonalInfo
import io.reactivex.Observable
import okhttp3.ResponseBody
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
    fun queryPersonalMessage(@Query("id") id: String, @Query("sfhm") loginName: String): Observable<Response<PersonalInfo>>

    @POST("app/tzszxx")
    fun getCourtTrend(@Query("page") page: Int, @Query("rows") rows: Int): Observable<Response<Page<CourtTrend>>>

    @POST("app/tzfjdownload")
    fun downFile(@Query("filename") filename: String, @Query("fileurl") fileurl: String, @Query("xsmc") xsmc: String): Observable<ResponseBody>


    @POST("app/recommendedTeachingMaterials")
    fun getTeachingMaterialByFydm(@Query("page") page: Int, @Query("rows") rows: Int, @Query("fydm") fydm: String): Observable<Response<Page<Material>>>

    @POST("app/downloadTeachingMaterials")
    fun downloadMaterial(@Query("filename") filename: String, @Query("fileurl") fileurl: String, @Query("xsmc") xsmc: String): Observable<ResponseBody>

    @POST("app/getTree")
    fun getCourt(): Observable<Response<List<Court>>>

    @POST("app/downloadRecordList")
    fun getDownloadTeachingMaterial(@Query("page") page: Int, @Query("rows") rows: Int, @Query("id") id: String): Observable<Response<Page<Material>>>

}