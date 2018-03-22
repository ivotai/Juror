package com.unicorn.juror.personalBusiness

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.courtTrend.CourtTrendFra
import com.unicorn.juror.interactArea.WebsiteInteractFra

class PersonalBusinessPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles = listOf("参审回执", "参审评价", "庭审直播")
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) CourtTrendFra() else WebsiteInteractFra()
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

}