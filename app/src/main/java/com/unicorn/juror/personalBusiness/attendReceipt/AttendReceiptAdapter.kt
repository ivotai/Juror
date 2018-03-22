package com.unicorn.juror.personalBusiness.attendReceipt

import android.annotation.SuppressLint
import com.afollestad.materialdialogs.MaterialDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.personalBusiness.model.Bs
import com.unicorn.juror.util.DialogUtils
import org.joda.time.DateTime

class AttendReceiptAdapter : BaseQuickAdapter<Bs, BaseViewHolder>(R.layout.item_attend_receipt) {

    override fun convert(helper: BaseViewHolder, item: Bs) {
        helper.apply {
            setText(R.id.tvAhqc, "案号全称:${item.ahqc}")
            setText(R.id.tvSqrmc, "申请人:${item.sqrmc}")
            setText(R.id.tvSqsj, "申请时间:${DateTime(item.sqtime).toString("yyyy-MM-dd")}")
            // 5确认出庭
            setOnClickListener(R.id.btnAgree, { replyAttend(5, item) })
            setOnClickListener(R.id.btnDisagree, { replyAttend(4, item) })
        }
    }

    @SuppressLint("CheckResult")
    private fun replyAttend(state: Int, item: Bs) {
        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .replyAttend(state = state, appId = AllTime.userInfo.id,
                        receiptId = item.id, cxrs = item.cxrs
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