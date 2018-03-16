package com.unicorn.juror.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.unicorn.juror.BuildConfig
import me.yokeyword.fragmentation.Fragmentation
import net.danlew.android.joda.JodaTimeAndroid

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install()
    }
}