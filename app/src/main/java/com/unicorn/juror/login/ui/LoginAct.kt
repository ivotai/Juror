package com.unicorn.juror.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.main.MainAct
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        etUsername.setText("61010019600501023x")
        etPassword.setText("0000")

        setIconLeft()
        bindIntent()
    }

    @SuppressLint("CheckResult")
    private fun bindIntent() {
        tvLogin.clicks().subscribe { login() }
    }


    // todo rxlifecycle
    @SuppressLint("CheckResult")
    private fun login() {
        ComponentHolder.appComponent.getLoginApi()
                .login(etUsername.text.toString(), etPassword.text.toString())
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            showLoading()
                        }
                        it.isError() -> {
                            hideLoading()
//                            Intent(this, MainAct::class.java).apply {
//                                startActivity(this)
//                            }
                        }
                        it.isSuccess() -> {
                            hideLoading()
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

    private lateinit var dialog: MaterialDialog

     fun showLoading() {
        dialog = MaterialDialog.Builder(this)
                .title("登录中...")
                .content("请稍后")
                .progress(true, 0)
                .show()
    }

     fun hideLoading() {
        dialog.dismiss()
    }

    private fun setIconLeft() {
        IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_person)
                .sizeDp(24)
                .let { etUsername.setIconLeft(it) }
        IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_locked_outline)
                .sizeDp(24)
                .let { etPassword.setIconLeft(it) }
    }

}
