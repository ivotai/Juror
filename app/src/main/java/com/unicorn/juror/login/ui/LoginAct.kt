package com.unicorn.juror.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import com.afollestad.materialdialogs.MaterialDialog
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
import com.unicorn.juror.registration.RegistrationAct
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutID = R.layout.act_login

    override fun initViews() {
        appBar.setTitle("登录")
        etUsername.setText("61010019600501023x")
        etPassword.setText("1234")
        setIcons()
        tvVisitor.paint.flags = Paint.UNDERLINE_TEXT_FLAG
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
        tvVisitor.clicks().subscribe {
            AllTime.isVisitor = true
            Intent(this, MainAct::class.java).let { startActivity(it) }
        }
        tvLogin.clicks().subscribe { login() }
        val subscribe = tvRegistration.clicks().subscribe {
            Intent(this, RegistrationAct::class.java).let { startActivity(it) }
        }
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
                            Intent(this, MainAct::class.java).apply {
                                startActivity(this)
                            }
                        }
                        it.isSuccess() -> {
                            mask?.dismiss()
                            val response = it.response!!
                            if (!response.flag) {
//                                ToastUtils.showShort(response.msg)
                            } else {
                                AllTime.userInfo = response.data
                                AllTime.isVisitor = false
                                Intent(this, MainAct::class.java).apply {
                                    startActivity(this)
                                }
                            }
                        }
                    }
                }
    }

}
