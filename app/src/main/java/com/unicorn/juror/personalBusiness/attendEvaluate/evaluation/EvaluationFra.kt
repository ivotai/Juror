package com.unicorn.juror.personalBusiness.attendEvaluate.evaluation

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.widget.LinearLayout
import com.flyco.roundview.RoundTextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.personalBusiness.attendEvaluate.evaluation.model.Option
import kotlinx.android.synthetic.main.fra_evaluation.*


class EvaluationFra : BaseFra() {

    override val layoutID: Int
        get() = R.layout.fra_evaluation

    // ================================ color ================================

     var white: Int = 0

     var blue: Int = 0

    private val rtvList = ArrayList<RoundTextView>()
    private val llList = ArrayList<LinearLayout>()

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        for (ll in llList)
            ll.setOnClickListener { select(llList.indexOf(it)) }
        RxTextView.afterTextChangeEvents(etAdvice)
                .map { it.editable().toString() }
                .subscribe { option.advice = it }
    }

    lateinit var option: Option

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        val position = arguments!!.getInt("position")
        option = AllTime.evaluationOptions!![position]

        initColor()

        rtvList.add(rtvOne)
        rtvList.add(rtvTwo)
        rtvList.add(rtvThree)
        rtvList.add(rtvFour)

        llList.add(llOne)
        llList.add(llTwo)
        llList.add(llThree)
        llList.add(llFour)

        tvContent.text = "${option.index}. ${option.content}"
        tvIndex.text = option.index.toString()
    }

    private fun initColor() {
        white = ContextCompat.getColor(activity!!, R.color.md_white)
        blue = ContextCompat.getColor(activity!!, R.color.colorAccent)
    }

    private fun select(index: Int) {
        option.result = index
        for (rtv in rtvList) {
            val indexTemp = rtvList.indexOf(rtv)
            if (indexTemp == index) {
                rtv.setTextColor(white)
                rtv.delegate.backgroundColor = blue
            } else {
                rtv.setTextColor(blue)
                rtv.delegate.backgroundColor = white
            }
        }
    }

}
