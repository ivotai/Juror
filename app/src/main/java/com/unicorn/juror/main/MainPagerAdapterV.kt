package com.unicorn.juror.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.courtTrend.CourtTrendFra
import com.unicorn.juror.education.EducationTrainingFra

class MainPagerAdapterV(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) =
            if (position == 0) CourtTrendFra()
            else EducationTrainingFra()

    override fun getCount() = 2

}