package com.unicorn.juror.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.main.news.NewsFra

class MainPagerAdapterForVisitor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return NewsFra()
    }

    override fun getCount() = 3

}