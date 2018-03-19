package com.unicorn.juror.education

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.education.model.Material

class MaterialAdapter : BaseQuickAdapter<Material, BaseViewHolder>(R.layout.item_book) {

    override fun convert(helper: BaseViewHolder, item: Material) {
        helper.setText(R.id.tvTitle, item.id)
        helper.setOnClickListener(R.id.tvContent, { downloadBook(item) })
    }

    private fun downloadBook(item: Material) {
//        ComponentHolder.appComponent.getLoginApi().downloadMaterial(item.file_name,item.)

    }

}