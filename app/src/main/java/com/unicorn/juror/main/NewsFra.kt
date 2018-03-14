package com.unicorn.juror.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra

class NewsFra : BaseFra() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fra_simple, container, false)
    }

}