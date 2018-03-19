package com.unicorn.juror.courtTrend

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import org.joda.time.DateTime

class CourtTrendAdapter : BaseQuickAdapter<CourtTrend, BaseViewHolder>(R.layout.item_trend) {
    override fun convert(helper: BaseViewHolder, item: CourtTrend) {
        item.apply {
            helper.setText(R.id.tvTitle, title)
            helper.setText(R.id.tvContent, content)
            helper.setText(R.id.tvFbTime, DateTime(fbtime).toString("yyyy-MM-dd"))
        }
    }
}