package com.unicorn.juror.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        setIconLeft()
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
