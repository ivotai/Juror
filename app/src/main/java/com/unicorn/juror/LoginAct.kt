package com.unicorn.juror

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        Intent(this,MainAct::class.java).apply{
            startActivity(this)
        }
    }

}
