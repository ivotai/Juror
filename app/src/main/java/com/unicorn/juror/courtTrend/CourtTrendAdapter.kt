package com.unicorn.juror.courtTrend

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.app.Constant
import com.unicorn.juror.courtTrend.comment.CommentAct
import org.joda.time.DateTime

class CourtTrendAdapter : BaseQuickAdapter<CourtTrend, BaseViewHolder>(R.layout.item_court_trend) {

    override fun convert(helper: BaseViewHolder, item: CourtTrend) {
        item.apply {
            helper.setText(R.id.tvTitle, title)
            helper.setText(R.id.tvContent, content)
            helper.setText(R.id.tvFbTime, DateTime(fbtime).toString("yyyy-MM-dd"))
            helper.setText(R.id.tvPlrs, "评论 $plzs")

            helper.setOnClickListener(R.id.item, {
                Intent(mContext, CommentAct::class.java).apply {
                    putExtra(Constant.COURT_TREND, item)
                    mContext.startActivity(this)
                }
            })
        }
    }

}