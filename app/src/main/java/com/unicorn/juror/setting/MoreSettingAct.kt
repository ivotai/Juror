package com.unicorn.juror.setting

import android.annotation.SuppressLint
import android.content.Intent
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import kotlinx.android.synthetic.main.act_more_setting.*

class MoreSettingAct : BaseAct() {

    override val layoutID = R.layout.act_more_setting

    override fun initViews() {
        appBar.setTitle("更多设置")
        appBar.showBackAction()
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        tvUpdatePwd.clicks().subscribe {
            Intent(this,UpdatePwdAct::class.java).apply {
                startActivity(this)
            }
        }
        tvAbout.clicks().subscribe {
            Intent(this,AboutAct::class.java).apply {
                startActivity(this)
            }
        }
    }

}
