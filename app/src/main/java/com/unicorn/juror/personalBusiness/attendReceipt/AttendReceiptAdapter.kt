package com.unicorn.juror.personalBusiness.attendReceipt

import android.annotation.SuppressLint
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hwangjr.rxbus.RxBus
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
            setText(R.id.tvLaah, "立案案号: ${item.ahqc}")
            setText(R.id.tvLaay, "案号案由: ${item.laaymc}")
            setText(R.id.tvLasj, "申请时间: ${item.larq}")
            setText(R.id.tvCbfg, "申请时间: ${item.cbrmc}")
            setText(R.id.tvTc, "庭次: ${item.ftmc} ${DateTime(item.kssj).toString("yyyy-MM-dd HH:mm")}—" +
                    "${DateTime(item.jssj).toString("HH:mm")}")
            // 5确认出庭
            setOnClickListener(R.id.btnAgree, {
                //                replyAttend(5, item) }
                MaterialDialog.Builder(mContext)
                        .title("确认出庭")
                        .positiveText("确认")
                        .negativeText("取消")
                        .onPositive(object : MaterialDialog.SingleButtonCallback {
                            override fun onClick(dialog: MaterialDialog, which: DialogAction) {
                                replyAttend(5, item)
                            }
                        }).show()

            })

            setOnClickListener(R.id.btnDisagree, {
                MaterialDialog.Builder(mContext)
                        .title("确认不出庭")
                        .positiveText("确认")
                        .negativeText("取消")
                        .onPositive { dialog, which -> replyAttend(4, item) }.show()
            })
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
                            RxBus.get().post("refreshAttendReceipt", Any())
                        }
                    }
                }
    }


}