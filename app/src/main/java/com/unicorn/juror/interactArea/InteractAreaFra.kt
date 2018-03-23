package com.unicorn.juror.interactArea

import android.annotation.SuppressLint
import android.view.View
import com.hwangjr.rxbus.RxBus
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.app.clicks
import com.unicorn.juror.main.NavigationCloseEvent
import kotlinx.android.synthetic.main.fra_education_training.*

class InteractAreaFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    @SuppressLint("CheckResult")
    override fun initViews() {
        appBar.setTitle("互动专区")
        if (!AllTime.isVisitor) {
            appBar.itvAction.text = "{ion-navicon}"
            appBar.itvAction.visibility = View.VISIBLE
            appBar.itvAction.clicks().subscribe { RxBus.get().post("closeDrawer", NavigationCloseEvent()) }
        }
        viewPager.adapter = InteractAreaPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {
    }

}