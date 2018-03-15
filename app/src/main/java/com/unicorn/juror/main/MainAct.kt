package com.unicorn.juror.main

import android.content.Intent
import android.graphics.Color
import com.afollestad.materialdialogs.MaterialDialog
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.login.ui.LoginAct
import com.unicorn.juror.main.navigationView.HeaderView
import com.unicorn.juror.setting.UpdatePwdAct
import kotlinx.android.synthetic.main.act_main.*
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView2


class MainAct : BaseAct() {

    override val layoutID = R.layout.act_main

    override fun initViews() {
        initNavigationView()
        initViewPager()
    }

    override fun bindIntent() {
        // nothing
    }

    private fun initNavigationView() {
        navigation.addHeaderView(HeaderView(context = this))
        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navItem1 -> logout()
                R.id.navItem2 -> Intent(this@MainAct, UpdatePwdAct::class.java).apply {
                    startActivity(this)
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun logout() {
        MaterialDialog.Builder(this)
                .title("确认登出")
                .positiveText("确认")
                .negativeText("取消")
                .onPositive { _, _ ->
                    finish()
                    Intent(this, LoginAct::class.java).let { startActivity(it) }
                }
                .show()
    }

    private fun initViewPager() {
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

    }

    private val defaultColor = Color.parseColor("#aeaeae")
    private val checkedColor = Color.parseColor("#d74c46")

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
