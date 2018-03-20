package com.unicorn.juror.education.court

import com.chad.library.adapter.base.entity.MultiItemEntity


data class Court(
        val dm: String,
        val dmms: String,
        val sjdm: String,
        var level: Int,
        var children: MutableList<Court> = ArrayList()
) : MultiItemEntity {

    override fun getItemType() = level

}