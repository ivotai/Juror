package com.unicorn.juror.education.court

import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hwangjr.rxbus.RxBus
import com.mikepenz.iconics.view.IconicsTextView
import com.unicorn.juror.R

class CourtAdapter : BaseMultiItemQuickAdapter<Court, BaseViewHolder>(null) {


    override fun convert(helper: BaseViewHolder, item: Court) {
//        when(item.itemType){
//            0 ->                 helper.setText(R.id.tvName,item.dmms)
//
//            1 -> ""
//            2 -> ""
        helper.setText(R.id.tvName, item.dmms);
        helper.setOnClickListener(R.id.itvAction, {
            if (helper.adapterPosition == -1) {
                return@setOnClickListener
            }
            if (item.isExpanded) {
                collapse(helper.adapterPosition)
            } else {
                expand(helper.adapterPosition)
            }
            notifyItemChanged(helper.adapterPosition)
        })
        helper.getView<IconicsTextView>(R.id.itvAction).visibility =
                if (item.children.isEmpty()) View.INVISIBLE else View.VISIBLE
        helper.getView<IconicsTextView>(R.id.itvAction).text =
                if (item.isExpanded) "{ion-arrow-up-b}" else "{ion-arrow-down-b}"

        helper.setOnClickListener(R.id.item, {
            RxBus.get().post("onCourtSelect", item)
            ActivityUtils.getTopActivity().finish()
        })
    }

    init {
        addItemType(0, R.layout.item_court)
        addItemType(1, R.layout.item_court2)
        addItemType(2, R.layout.item_court3)
    }
}