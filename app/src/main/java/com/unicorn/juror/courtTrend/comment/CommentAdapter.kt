package com.unicorn.juror.courtTrend.comment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R

class CommentAdapter : BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.item_comment) {

    override fun convert(helper: BaseViewHolder, item: Comment) {
        item.apply {
            helper.setText(R.id.tvUsername, userId)
            helper.setText(R.id.tvContent, content)
        }
    }

}