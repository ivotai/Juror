package com.unicorn.juror.education

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.main.SimpleFra

class EducationTrainingPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles = listOf("教材库", "我的书架")
    }

    override fun getItem(position: Int): Fragment {
        return SimpleFra()
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

}