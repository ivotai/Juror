package com.unicorn.juror.interactArea

import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_education_training.*

class InteractAreaFra : BaseFra() {

    override val layoutID = R.layout.fra_education_training

    override fun initViews() {
        appBar.setTitle("互动专区")
        viewPager.adapter = InteractAreaPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {

    }

}