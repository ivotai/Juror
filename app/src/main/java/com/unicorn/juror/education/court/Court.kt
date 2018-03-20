package com.unicorn.juror.education.court

import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity


data class Court(
        val dm: String,
        val dmms: String,
        val sjdm: String,
        var courtLevel: Int,
        var children: MutableList<Court> = ArrayList()
) : MultiItemEntity,IExpandable<Court> {

    override fun getItemType() = courtLevel

    override fun getSubItems(): MutableList<Court> {
        return children
    }

    override fun isExpanded(): Boolean {
      return _expanded
    }

    override fun getLevel(): Int {
        return courtLevel
    }

    private var _expanded = false

    override fun setExpanded(expanded: Boolean) {
        this._expanded = expanded
    }

}