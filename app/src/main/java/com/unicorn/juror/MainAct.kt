package com.unicorn.juror

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_main.*
import me.majiajie.pagerbottomtabstrip.item.NormalItemView
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem



class MainAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)


        val navigationController = tab.custom()
                .addItem(newItem(R.mipmap.iphone,R.mipmap.iphone2,"iphone"))
                .addItem(newItem(R.mipmap.android,R.mipmap.android2,"iphone"))

//                .addItem(R.mipmap.android, "android")
//                .addItem(R.mipmap.ipad, "ipad")
//                .addItem(R.mipmap.iphone, "iphone")
//                .addItem(R.mipmap.tablet, "tablet")
//                .addItem(R.mipmap.touchscreen, "touchscreen")
                .build()
        navigationController.setupWithViewPager(viewPager)
    }

    //创建一个Item
    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(this)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(Color.GRAY)
        normalItemView.setTextCheckedColor(-0xff6978)
        return normalItemView
    }

}
