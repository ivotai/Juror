package com.unicorn.juror.personalBusiness.attendEvaluate.evaluation

import android.annotation.SuppressLint
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.hwangjr.rxbus.RxBus
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.util.DialogUtils
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
            if (option.result == -1) {
                viewPager.setCurrentItem(option.index - 1, true)
                ToastUtils.showShort("该条目尚未评议")
                flag = false
                break
            }
        }
        if (!flag) return
        MaterialDialog.Builder(this)
                .title("确认提交评议?")
                .positiveText("确认")
                .negativeText("取消")
                .onPositive { _, _ -> realSubmit() }
                .show()
    }

    @SuppressLint("CheckResult")
    private fun realSubmit() {
        val options = AllTime.evaluationOptions!!
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .evaluate(psyid = AllTime.userInfo.id, psymc = AllTime.userInfo.userName,
                        pynr = "", ajbs = intent.getStringExtra("ajbs"),
                        fgtsly = options[0].result, fgtsjl = options[1].result, tqjy = options[2].result,
                        dpsyydnl = options[3].result, spywnl = options[4].result, ljzl = options[5].result,
                        sflyyjhjy = options[0].advice, spjlyjhjy = options[1].advice, tsjyyjhjy = options[2].advice,
                        dpsyydnlyjhjy = options[3].advice, spywnlyjhjy = options[4].advice, ljzlyjhjy = options[5].advice
                )
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            mask = DialogUtils.showLoading(context = this, title = "提交中...")
                        }
                        it.isError() -> {
                            mask?.dismiss()
                        }
                        it.isSuccess() -> {
                            mask?.dismiss()
                            RxBus.get().post("refreshAttendEvaluate", Any())
                            finish()
                        }
                    }
                }
    }

    override fun onDestroy() {
        AllTime.evaluationOptions = null
        super.onDestroy()
    }
}