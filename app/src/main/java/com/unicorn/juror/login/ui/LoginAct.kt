package com.unicorn.juror.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.bottomSheet.DialogUtils
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.main.MainAct
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutID = R.layout.act_login

    override fun initViews() {
        etUsername.setText("61010019600501023x")
        etPassword.setText("0000")
        setIcons()
    }

    private fun setIcons() {
        IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_person)
                .sizeDp(24)
                .let { etUsername.setIconLeft(it) }
        IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_locked_outline)
                .sizeDp(24)
                .let { etPassword.setIconLeft(it) }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        tvLogin.clicks().subscribe { login() }
    }

    // todo rxlifecycle
    @SuppressLint("CheckResult")
    private fun login() {
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .login(etUsername.text.toString(), etPassword.text.toString())
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            mask = DialogUtils.showLoading(context = this, title = "登录中...")
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
                                Intent(this, MainAct::class.java).apply {
                                    startActivity(this)
                                }
                            }
                        }
                    }
                }
    }

}
