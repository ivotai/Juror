package com.unicorn.juror.education.material

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.education.model.Material
import org.joda.time.DateTime

class MaterialAdapter : BaseQuickAdapter<Material, BaseViewHolder>(R.layout.item_book) {

    override fun convert(helper: BaseViewHolder, item: Material) {
        helper.setText(R.id.tvTitle, item.title)
        helper.setText(R.id.tvContent, item.content)
        helper.setText(R.id.tvPublicTime, DateTime(item.pulc_time).toString("yyyy-MM-dd"))

        helper.setOnClickListener(R.id.item, {
            if (item.isExist) item.open(mContext)
            else item.download(mContext)
        })
    }


}