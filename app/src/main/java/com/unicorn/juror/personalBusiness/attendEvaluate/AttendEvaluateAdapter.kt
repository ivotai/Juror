package com.unicorn.juror.personalBusiness.attendEvaluate

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hwangjr.rxbus.RxBus
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.personalBusiness.attendEvaluate.evaluation.EvaluationAct
import com.unicorn.juror.personalBusiness.model.Bs2
import com.unicorn.juror.util.DialogUtils
import org.joda.time.DateTime


class AttendEvaluateAdapter : BaseQuickAdapter<Bs2, BaseViewHolder>(R.layout.item_attend_evaluate) {

    override fun convert(helper: BaseViewHolder, item: Bs2) {
        helper.apply {
            setText(R.id.tvLaah, "立案案号: ${item.ahqc}")
            setText(R.id.tvLaay, "案号案由: ${item.laaymc}")
            setText(R.id.tvLasj, "申请时间: ${item.larq}")
            setText(R.id.tvCbfg, "申请人: ${item.cbrmc}")
            setText(R.id.tvTc, "庭次: ${item.ftmc} ${DateTime(item.kssj).toString("yyyy-MM-dd HH:mm")}—" + DateTime(item.jssj).toString("HH:mm"))

            val btnEvaluate = helper.getView<TextView>(R.id.btnEvaluate)
//            btnEvaluate.visibility = if (item.ispy == 0) View.VISIBLE else View.INVISIBLE
            val btnFactFinding = helper.getView<TextView>(R.id.btnFactFinding)
            btnFactFinding.visibility = if (item.isssrd == 0) View.VISIBLE else View.INVISIBLE
//            if (btnEvaluate.visibility == View.INVISIBLE &&
//                    btnFactFinding.visibility == View.INVISIBLE) {
//                val ll = helper.getView<LinearLayout>(R.id.llBtn)
//                ll.visibility = View.GONE
//            }

            setOnClickListener(R.id.btnEvaluate, { evaluate(item) })
            setOnClickListener(R.id.btnFactFinding, { factFinding(item) })
        }
    }

    private fun evaluate(item: Bs2) {
        Intent(mContext, EvaluationAct::class.java)
                .apply { }
                .let { mContext.startActivity(it) }
//        MaterialDialog.Builder(mContext)
//                .title("我的评议")
//                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input("输入评议内容", "", MaterialDialog.InputCallback { dialog, input ->
//                        evaluate_(input.toString(), item.ajbs)
//                })
//                .show()
    }

    @SuppressLint("CheckResult")
    private fun evaluate_(pynr: String, ajbs: String) {
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .evaluate(psyid = AllTime.userInfo.id, psymc = AllTime.userInfo.userName,
                        pynr = pynr, ajbs = ajbs
                )
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            mask = DialogUtils.showLoading(context = mContext, title = "提交中...")
                        }
                        it.isError() -> {
                            mask?.dismiss()
                        }
                        it.isSuccess() -> {
                            mask?.dismiss()
                            RxBus.get().post("refreshAttendEvaluate", Any())
                        }
                    }
                }
    }

    lateinit var etAdvice: EditText
    lateinit var radioGroup: RadioGroup

    private fun factFinding(item: Bs2) {
        val wrapInScrollView = true
        val dialog = MaterialDialog.Builder(mContext)
                .title("事实认定")
                .customView(R.layout.custom_dialog, wrapInScrollView)
                .positiveText("确定")
                .onPositive { dialog, which ->
                    val yj = when (radioGroup.checkedRadioButtonId) {
                        R.id.rbYes -> 1
                        R.id.rbNo -> 2
                        else -> 3
                    }
//                    ToastUtils.showShort(yj.toString())
                    factFinding_(yj, etAdvice.text.toString(), item.ajbs)
                }
                .show()

        etAdvice = dialog.customView!!.findViewById<EditText>(R.id.etAdvice)
        radioGroup = dialog.customView!!.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            etAdvice.visibility = if (checkedId == R.id.rbYes) View.GONE else View.VISIBLE
        }
//        MaterialDialog.Builder(mContext)
//                .title("事实认定")
//                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input("输入意见内容", "", MaterialDialog.InputCallback { dialog, input ->
//                    this.input2 = input.toString()
//                })
//                .onPositive(object : MaterialDialog.SingleButtonCallback {
//                    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
//                        factFinding_(input2, item.ajbs)
//                    }
//                })
//                .show()
    }


    @SuppressLint("CheckResult")
    private fun factFinding_(yj: Int, yjnr: String, ajbs: String) {
        // todo 意见：认定，不认定，部分认定
        // todo 认定 无内容
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .fackFinding(psyid = AllTime.userInfo.id, psymc = AllTime.userInfo.userName,
                        yjnr = yjnr, ajbs = ajbs, yj = yj
                )
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            mask = DialogUtils.showLoading(context = mContext, title = "提交中...")
                        }
                        it.isError() -> {
                            mask?.dismiss()
                        }
                        it.isSuccess() -> {
                            RxBus.get().post("refreshAttendEvaluate", Any())
                            mask?.dismiss()
                        }
                    }
                }
    }

}