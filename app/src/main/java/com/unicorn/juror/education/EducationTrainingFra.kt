package com.unicorn.juror.education

import android.content.Intent
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.education.court.CourtAct
import kotlinx.android.synthetic.main.fra_education_training.*

class EducationTrainingFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    override fun initViews() {
        appBar.setTitle("教育培训")
        viewPager.adapter = EducationTrainingPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.text = "教材库(明江法院)"

        appBar.setOnClickListener {
            Intent(context, CourtAct::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun bindIntent() {

    }

}