package com.unicorn.juror.education

import android.content.Intent
import android.os.Bundle
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.education.court.Court
import com.unicorn.juror.education.court.CourtAct
import kotlinx.android.synthetic.main.fra_education_training.*

class EducationTrainingFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    override fun initViews() {
        appBar.setTitle("教育培训")
        viewPager.adapter = if (AllTime.isVisitor) EducationTrainingPagerAdapterV(childFragmentManager)
        else EducationTrainingPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.text = "教材库"

        appBar.setOnClickListener {
            Intent(context, CourtAct::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun bindIntent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    @Subscribe(tags = [(Tag("onCourtSelect"))])
    fun onCourtSelect(court: Court) {
        tabLayout.getTabAt(0)!!.text = court.dmms
    }

}