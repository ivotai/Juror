package com.unicorn.juror.registration

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.main.trend.TrendFra

class RegistrationPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val titles = listOf("个人信息", "进度查询")

    override fun getItem(position: Int): Fragment {
        return if(position==0) PersonalInfoFra() else TrendFra()
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

}