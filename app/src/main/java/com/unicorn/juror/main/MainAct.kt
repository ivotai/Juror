package com.unicorn.juror.main

import android.graphics.Color
import android.os.Bundle
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.main.navigationView.HeaderView
import kotlinx.android.synthetic.main.act_main.*
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView2


class MainAct : BaseAct() {

    private val defaultColor = Color.parseColor("#aeaeae")
    private val checkedColor = Color.parseColor("#d74c46")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val navigationController = tab.custom()
                .addItem(newItem(Ionicons.Icon.ion_fireball, Ionicons.Icon.ion_fireball, "热点资讯"))
                .addItem(newItem(Ionicons.Icon.ion_university, Ionicons.Icon.ion_university, "报名申请"))
                .addItem(newItem(Ionicons.Icon.ion_ios_people, Ionicons.Icon.ion_ios_people, "互动专区"))
                .addItem(newItem(Ionicons.Icon.ion_ios_bookmarks, Ionicons.Icon.ion_ios_bookmarks, "教育培训"))
                .addItem(newItem(Ionicons.Icon.ion_android_person, Ionicons.Icon.ion_android_person, "个人业务"))
                .build()
        navigationController.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 5 - 1
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)

        navigation.addHeaderView(HeaderView(context = this))
    }

    private fun newItem(default: IIcon, checked: IIcon, text: String): BaseTabItem {
        val normalItemView = NormalItemView2(this)
        normalItemView.initialize(
                IconicsDrawable(this)
                        .icon(default)
                        .color(defaultColor)
                        .sizeDp(24),
                IconicsDrawable(this)
                        .icon(checked)
                        .color(checkedColor)
                        .sizeDp(24),
                text)
        normalItemView.setTextDefaultColor(defaultColor)
        normalItemView.setTextCheckedColor(checkedColor)
        return normalItemView
    }

}
