package com.unicorn.juror.login.api

import com.unicorn.juror.app.Page
import com.unicorn.juror.app.Response
import com.unicorn.juror.courtTrend.CourtTrend
import com.unicorn.juror.courtTrend.comment.Comment
import com.unicorn.juror.education.court.Court
import com.unicorn.juror.education.model.Material
import com.unicorn.juror.login.model.UserInfo
import com.unicorn.juror.personalBusiness.model.Bs
import com.unicorn.juror.personalBusiness.model.Bs2
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
    fun register(@Query("name") name: String, @Query("xb") sex: String, @Query("jg") nativelyPlace: String, @Query("mzdm") mzdm: String,
                 @Query("birthday") birthday: String, @Query("sfhm") identifyCard: String, @Query("lxsj") telephone: String, @Query("zz") address: String)
            : Observable<Response<Any>>

    @POST("app/queryPersonMessage")
    fun queryPersonalMessage(@Query("id") id: String, @Query("sfhm") loginName: String): Observable<Response<PersonalInfo>>

    @POST("app/tzszxx")
    fun getCourtTrend(@Query("page") page: Int, @Query("rows") rows: Int): Observable<Response<Page<CourtTrend>>>

    @POST("app/tzfjdownload")
    fun downAttachment(@Query("filename") filename: String, @Query("fileurl") fileurl: String, @Query("xsmc") xsmc: String): Observable<ResponseBody>

    @POST("app/recommendedTeachingMaterials")
    fun getTeachingMaterialByFydm(@Query("page") page: Int, @Query("rows") rows: Int, @Query("fydm") fydm: String): Observable<Response<Page<Material>>>

    @POST("app/downloadTeachingMaterials")
    fun downloadMaterial(@Query("filename") filename: String, @Query("fileurl") fileurl: String, @Query("xsmc") xsmc: String,
                         @Query("appid") appid: String, @Query("trainingid") trainingid: String): Observable<ResponseBody>

    @POST("app/getTree")
    fun getCourt(): Observable<Response<List<Court>>>

    @POST("app/downloadRecordList")
    fun getDownloadTeachingMaterial(@Query("page") page: Int, @Query("rows") rows: Int, @Query("appid") appid: String): Observable<Response<Page<Material>>>

    @POST("app/addPl")
    fun addComment(@Query("appid") appId: String, @Query("trainingid") courtTrendId: String, @Query("plnr") content: String): Observable<Response<Any>>

    @POST("app/plList")
    fun getComment(@Query("page") page: Int, @Query("rows") rows: Int, @Query("msgid") courtTrendId: String): Observable<Response<Page<Comment>>>

    @POST("app/calcxxsc")
    fun addStudyRecord(@Query("appid") appId: String, @Query("trainingid") trainingid: String,
                       @Query("startTime") startTime: String, @Query("endTime") endTime: String): Observable<Response<Any>>

    @POST("app/cshzList")
    fun getAttendReceipt(@Query("page") page: Int, @Query("rows") rows: Int, @Query("appid") appId: String): Observable<Response<Page<Bs>>>

    @POST("app/pstz")
    fun replyAttend(@Query("state") state: Int, @Query("appid") appId: String,
                    @Query("id") receiptId: String, @Query("cxrs") cxrs: Int): Observable<Response<Any>>

    @POST("app/cspyList")
    fun getAttendEvaluate(@Query("page") page: Int, @Query("rows") rows: Int, @Query("appid") appId: String): Observable<Response<Page<Bs2>>>

    @POST("app/savePy")
    fun evaluate(@Query("psyid") psyid: String, @Query("psymc") psymc: String,
                 @Query("pynr") pynr: String, @Query("ajbs") ajbs: String,

                 @Query("fgtsly") fgtsly: Int, @Query("fgtsjl") fgtsjl: Int, @Query("tqjy") tqjy: Int,
                 @Query("dpsyydnl") dpsyydnl: Int, @Query("spywnl") spywnl: Int, @Query("ljzl") ljzl: Int,

                 @Query("sflyyjhjy") sflyyjhjy: String, @Query("spjlyjhjy") spjlyjhjy: String, @Query("tsjyyjhjy") tsjyyjhjy: String,
                 @Query("dpsyydnlyjhjy") dpsyydnlyjhjy: String, @Query("spywnlyjhjy") spywnlyjhjy: String, @Query("ljzlyjhjy") ljzlyjhjy: String
    ): Observable<Response<Any>>

    @POST("app/saveSsrdqd")
    fun fackFinding(@Query("psyid") psyid: String, @Query("psymc") psymc: String,
                    @Query("yjnr") yjnr: String, @Query("ajbs") ajbs: String, @Query("yj") yj: Int): Observable<Response<Any>>


}