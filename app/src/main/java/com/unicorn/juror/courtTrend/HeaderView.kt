package com.unicorn.juror.courtTrend

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.juror.R

class HeaderView : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var tvContent: TextView
    var tvAttachment: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.header_court_trend, this)
        tvContent = view.findViewById(R.id.tvContent)
        tvAttachment = view.findViewById(R.id.tvAttachment)
        tvAttachment.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        tvAttachment.paint.isAntiAlias = true
    }

}