package com.unicorn.juror.education

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.juror.education.material.DownloadMaterialFra
import com.unicorn.juror.education.material.CountMaterialFra

class EducationTrainingPagerAdapterV(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles = listOf("教材库")
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) CountMaterialFra() else DownloadMaterialFra()
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

}