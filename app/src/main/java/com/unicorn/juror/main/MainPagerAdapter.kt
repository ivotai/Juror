package com.unicorn.juror.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.courtTrend.CourtTrendFra
import com.unicorn.juror.education.EducationTrainingFra
import com.unicorn.juror.registration.RegistrationFra

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> RegistrationFra()
        1 -> CourtTrendFra()
        2 -> EducationTrainingFra()
        else -> SimpleFra()
    }

    override fun getCount() = 4

}