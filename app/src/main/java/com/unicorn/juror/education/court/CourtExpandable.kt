package com.unicorn.juror.education.court

import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity

class CourtExpandable(val court: Court, val l: Int) : IExpandable<Court>,MultiItemEntity {

    override fun getSubItems(): MutableList<Court> {
        return court.children
    }

    override fun isExpanded(): Boolean {
        return false
    }

    override fun getLevel(): Int {
        return l
    }

    override fun setExpanded(expanded: Boolean) {
    }

    override fun getItemType(): Int {
       return  0
    }
}