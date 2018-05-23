package com.unicorn.juror.app

import com.unicorn.juror.login.model.UserInfo
import com.unicorn.juror.personalBusiness.attendEvaluate.evaluation.model.Option

object AllTime {

    lateinit var userInfo: UserInfo

    var isVisitor = true

    const val R00 = "R00"

    var evaluationOptions: List<Option>? = null

}