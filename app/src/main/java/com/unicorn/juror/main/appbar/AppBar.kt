package com.unicorn.juror.main.appbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ActivityUtils
import com.mikepenz.iconics.view.IconicsTextView
import com.unicorn.juror.R
import com.unicorn.juror.app.clicks

class AppBar(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var tvTitle: TextView
    private var itvAction: IconicsTextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.app_bar, this)
        tvTitle = view.findViewById(R.id.tvTitle)
        itvAction = view.findViewById(R.id.itvAction)
        itvAction.clicks().subscribe { ActivityUtils.getTopActivity().finish() }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun showBackAction(){
        itvAction.visibility = View.VISIBLE
    }

}