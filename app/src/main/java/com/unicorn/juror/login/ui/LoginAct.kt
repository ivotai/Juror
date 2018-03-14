package com.unicorn.juror.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import com.unicorn.juror.app.clicks
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.main.MainAct
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        setIconLeft()
        bindIntent()
    }

    @SuppressLint("CheckResult")
    private fun bindIntent() {
        tvLogin.clicks().subscribe { login() }
    }

    private val repo = ComponentHolder.appComponent.getLoginRepo()

    // todo rxlifecycle
    @SuppressLint("CheckResult")
    private fun login() {
        Intent(this, MainAct::class.java).apply {
            startActivity(this)
//        repo.login().subscribe {
//            when {
//                it.isLoading() -> {
//                    showLoading()
//                }
//                it.isError() -> {
//                    hideLoading()
//
//                }
//                it.isSuccess() -> {
//                    hideLoading()
//
////                    tieAdapter.setNewData(it.data)
//                    Intent(this, MainAct::class.java).apply {
//                        startActivity(this)
//                    }
//                }
//            }
        }
    }

    private lateinit var dialog: MaterialDialog

    override fun showLoading() {
        dialog = MaterialDialog.Builder(this)
                .title("登录中...")
                .content("请稍后")
                .progress(true, 0)
                .show()
    }

    override fun hideLoading() {
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
