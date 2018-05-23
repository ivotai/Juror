package com.unicorn.juror.personalBusiness.attendEvaluate.evaluation

import android.annotation.SuppressLint
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import kotlinx.android.synthetic.main.act_evaluation.*

class EvaluationAct : BaseAct() {

    override val layoutID = R.layout.act_evaluation

    @SuppressLint("CheckResult")
    override fun initViews() {
        AllTime.evaluationOptions = EvaluationOptionProvider().getNewOptions()

        appBar.setTitle("我的评议")
        appBar.showBackAction()

        appBar.itvAction2.visibility = View.VISIBLE
        appBar.itvAction2.text = "{ion-checkmark}"
        appBar.itvAction2.textSize = ConvertUtils.dp2px(8f).toFloat()
        appBar.itvAction2.clicks().subscribe { submit() }

        viewPager.adapter = EvaluationPagerAdapter(supportFragmentManager)
    }

    override fun bindIntent() {
    }

    private fun submit() {
        var flag = true
        for (option in AllTime.evaluationOptions!!) {
            if (option.result == -1 ) {
                viewPager.setCurrentItem(option.index-1, true)
                ToastUtils.showShort("该条目尚未评议")
                flag = false
                break
            }
        }
        if (!flag) return
        realSubmit()
    }

    private fun realSubmit() {

    }

    override fun onDestroy() {
        AllTime.evaluationOptions = null
        super.onDestroy()
    }
}