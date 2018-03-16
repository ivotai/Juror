package com.unicorn.juror.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.main.trend.TrendFra
import com.unicorn.juror.registration.PersonalInfoFra

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> TrendFra()
        1 -> PersonalInfoFra()
        else -> SimpleFra()
    }

    override fun getCount() = 5

}