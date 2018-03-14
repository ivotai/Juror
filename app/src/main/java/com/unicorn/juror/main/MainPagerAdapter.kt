package com.unicorn.juror.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainPagerAdapter:FragmentStatePagerAdapter{
    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment {
        return NewsFra()
    }

    override fun getCount() = 5

}