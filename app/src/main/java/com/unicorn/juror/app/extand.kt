package com.unicorn.juror.app

import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

fun TextView.clicks() = RxView.clicks(this).throttleFirst(1, TimeUnit.SECONDS)
