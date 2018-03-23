package com.unicorn.juror.personalBusiness.attendEvaluate

import android.annotation.SuppressLint
import android.text.InputType
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.personalBusiness.model.Bs2
import com.unicorn.juror.util.DialogUtils
import org.joda.time.DateTime


class AttendEvaluateAdapter : BaseQuickAdapter<Bs2, BaseViewHolder>(R.layout.item_attend_evaluate) {

    override fun convert(helper: BaseViewHolder, item: Bs2) {
        helper.apply {
            setText(R.id.tvAhqc, "案号全称:${item.ahqc}")
//            setText(R.id.tvLaaymc, "申请人:${item.laaymc}")
            setText(R.id.tvKssj, "申请时间:${DateTime(item.kssj).toString("yyyy-MM-dd")}")
            setOnClickListener(R.id.btnEvaluate, { evaluate(item) })
//            setOnClickListener(R.id.btnDisagree, { replyAttend(4, item) })
        }
    }


     var input:String = ""
    private fun evaluate(item: Bs2) {
        MaterialDialog.Builder(mContext)
                .title("我的评议")
                .content("输入评议内容")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("输入评议内容", "", MaterialDialog.InputCallback { dialog, input ->
                    this.input = input.toString()
                })
                .onPositive(object :MaterialDialog.SingleButtonCallback{
                    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
                            evaluate_(input,item.ajbs)
                    }
                })
                .show()
    }

    @SuppressLint("CheckResult")
    private fun evaluate_(pynr:String,ajbs:String){
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .evaluate(psyid = AllTime.userInfo.id,psymc = AllTime.userInfo.userName,
                        pynr = pynr,ajbs = ajbs
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

                        }
                    }
                }
    }

}