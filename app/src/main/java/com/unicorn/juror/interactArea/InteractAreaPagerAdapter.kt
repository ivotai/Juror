package com.unicorn.juror.interactArea

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.courtTrend.CourtTrendFra

class InteractAreaPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles = listOf("文化交流", "网站互动")
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) CourtTrendFra() else CourtTrendFra()
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

}