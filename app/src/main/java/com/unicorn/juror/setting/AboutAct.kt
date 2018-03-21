package com.unicorn.juror.setting

import com.blankj.utilcode.util.AppUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import kotlinx.android.synthetic.main.act_about.*

class AboutAct : BaseAct() {

    override val layoutID = R.layout.act_about

    override fun initViews() {
        appBar.setTitle("关于")
        appBar.showBackAction()
        tvAppVersion.text = AppUtils.getAppVersionName()
    }

    override fun bindIntent() {
    }

}
