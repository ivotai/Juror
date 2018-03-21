package com.unicorn.juror.setting

import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import kotlinx.android.synthetic.main.act_more_setting.*

class MoreSettingAct : BaseAct() {

    override val layoutID = R.layout.act_more_setting

    override fun initViews() {
        appBar.setTitle("更多设置")
        appBar.showBackAction()
    }

    override fun bindIntent() {

    }

}
