package com.unicorn.juror.courtTrend.comment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import org.joda.time.DateTime

class CommentAdapter : BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.item_comment) {

    override fun convert(helper: BaseViewHolder, item: Comment) {
        item.apply {
            helper.setText(R.id.tvUsername, name)
            helper.setText(R.id.tvContent, plnr)
            helper.setText(R.id.tvTime, DateTime(plsj).toString("yyyy-MM-dd hh:mm"))
        }
    }

}