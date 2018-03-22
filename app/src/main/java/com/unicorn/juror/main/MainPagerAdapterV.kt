package com.unicorn.juror.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.education.EducationTrainingFra
import com.unicorn.juror.interactArea.InteractAreaFra

class MainPagerAdapterV(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) =
            if (position == 0) InteractAreaFra()
            else EducationTrainingFra()

    override fun getCount() = 2

}