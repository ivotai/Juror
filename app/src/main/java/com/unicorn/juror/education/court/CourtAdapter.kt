package com.unicorn.juror.education.court

import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hwangjr.rxbus.RxBus
import com.unicorn.juror.R

class CourtAdapter : BaseMultiItemQuickAdapter<Court, BaseViewHolder>(null) {


    override fun convert(helper: BaseViewHolder, item: Court) {
//        when(item.itemType){
//            0 ->                 helper.setText(R.id.tvName,item.dmms)
//
//            1 -> ""
//            2 -> ""
        helper.setText(R.id.tvName, item.dmms);
        helper.setOnClickListener(R.id.tvName, {
            RxBus.get().post("onCourtSelect", item)
            ActivityUtils.getTopActivity().finish()
        })
//        }
    }

    init {
        addItemType(0, R.layout.item_court)
        addItemType(1, R.layout.item_court2)
        addItemType(2, R.layout.item_court3)
    }
}