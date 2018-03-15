package com.unicorn.juror.main.navigationView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R

class HeaderView : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.navigation_header, this)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        GradientDrawable().apply {
            cornerRadius = 1000f
            setColor(Color.parseColor("#1063b4"))
            setStroke(ConvertUtils.dp2px(1f), Color.parseColor("#6cabe7"))
        }.let { tvName.background = it }
    }

}