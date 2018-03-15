package com.unicorn.juror.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import kotlinx.android.synthetic.main.activity_update_pwd.*

class UpdatePwdAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pwd)

        tvSubmit.clicks().subscribe { updatePassword() }
    }

    private lateinit var dialog: MaterialDialog

    fun showLoading() {
        dialog = MaterialDialog.Builder(this)
                .title("提交中...")
                .content("请稍后")
                .progress(true, 0)
                .show()
    }

    fun hideLoading() {
        dialog.dismiss()
    }


    // todo rxlifecycle
    @SuppressLint("CheckResult")
    private fun updatePassword() {
        ComponentHolder.appComponent.getLoginApi()
        .updatePassword(AllTime.userInfo.userName, etOldPwd.text.toString(), etNewPwd.text.toString())
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            showLoading()
                        }
                        it.isError() -> {
                            hideLoading()
                        }
                        it.isSuccess() -> {
                            hideLoading()
                            val response = it.response!!
                            if (!response.flag) {
                                ToastUtils.showShort(response.msg)
                            } else {
                                AllTime.userInfo = response.data
//                                Intent(this, MainAct::class.java).apply {
//                                    startActivity(this)
//                                }
                            }
                        }
                    }
                }
    }

}
