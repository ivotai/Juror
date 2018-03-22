package com.unicorn.juror.personalBusiness

import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_education_training.*

class PersonalBusinessFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    override fun initViews() {
        appBar.setTitle("个人业务")
        viewPager.adapter = PersonalBusinessPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {

    }

}