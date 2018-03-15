package com.unicorn.juror.setting

import android.annotation.SuppressLint
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.bottomSheet.DialogUtils
import com.unicorn.juror.dagger.ComponentHolder
import kotlinx.android.synthetic.main.act_update_pwd.*

class UpdatePwdAct : BaseAct() {

    override val layoutID = R.layout.act_update_pwd

    override fun initViews() {
        // nothing
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        tvSubmit.clicks().subscribe { updatePassword() }
    }

    // todo rxlifecycle
    @SuppressLint("CheckResult")
    private fun updatePassword() {
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .updatePwd(AllTime.userInfo.loginName, etOldPwd.text.toString(), etNewPwd.text.toString())
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            mask = DialogUtils.showLoading(context = this, title = "修改密码中...")
                        }
                        it.isError() -> {
                            mask?.dismiss()
                        }
                        it.isSuccess() -> {
                            mask?.dismiss()
                            val response = it.response!!
                            if (!response.flag) {
                                ToastUtils.showShort(response.msg)
                            } else {
                                AllTime.userInfo = response.data
                            }
                        }
                    }
                }
    }

}
