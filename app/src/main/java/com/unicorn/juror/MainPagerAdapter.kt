package com.unicorn.juror

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainPagerAdapter:FragmentStatePagerAdapter{
    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment {
        return  Fragment()
    }

    override fun getCount() = 5

}