package com.unicorn.juror.app

import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseAct : SupportActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        initViews()
        bindIntent()
    }

    abstract val layoutID: Int

    abstract fun initViews()

    abstract fun bindIntent()

}