package com.unicorn.juror.personalBusiness.attendEvaluate.evaluation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class EvaluationPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val fra = EvaluationFra()
        Bundle().apply { putInt("position", position) }.let { fra.arguments = it }
        return fra
    }

    override fun getCount() = 6

}
