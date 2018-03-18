package com.unicorn.juror.education

import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_education_training.*

class EducationTrainingFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    override fun initViews() {
        appBar.setTitle("教育培训")
        viewPager.adapter = EducationTrainingPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.text = "教材库(明江法院)"

    }

    override fun bindIntent() {

    }

}