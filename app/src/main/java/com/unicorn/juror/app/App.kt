package com.unicorn.juror.app

import android.app.Application
import com.unicorn.juror.BuildConfig
import me.yokeyword.fragmentation.Fragmentation

class App:Application(){
    override fun onCreate() {
        super.onCreate()

        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
        .install()
    }
}