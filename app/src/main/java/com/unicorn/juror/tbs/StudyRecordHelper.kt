package com.unicorn.juror.tbs

import android.annotation.SuppressLint
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder

class StudyRecordHelper{

    @SuppressLint("CheckResult")
    fun addRecord(materailId: String, startTime: String, endTime: String) {
        ComponentHolder.appComponent.getLoginApi()
                .addStudyRecord(AllTime.userInfo.id, materailId, startTime, endTime)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            it
                        }
                        it.isError() -> {
                            it
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
                        }
                    }
                }
    }

}