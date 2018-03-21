package com.unicorn.juror.courtTrend

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.app.Constant
import org.joda.time.DateTime

class CourtTrendAdapter : BaseQuickAdapter<CourtTrend, BaseViewHolder>(R.layout.item_trend) {

    override fun convert(helper: BaseViewHolder, item: CourtTrend) {
        item.apply {
            helper.setText(R.id.tvTitle, title)
            helper.setText(R.id.tvContent, content)
            helper.setText(R.id.tvFbTime, DateTime(fbtime).toString("yyyy-MM-dd"))

            helper.setOnClickListener(R.id.tvTitle, {
                Intent(mContext, CourtTrendDetailAct::class.java).apply {
                    putExtra(Constant.COURT_TREND, item)
                    mContext.startActivity(this)
                }
            })
        }
    }

}