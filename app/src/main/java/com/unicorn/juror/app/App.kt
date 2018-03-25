package com.unicorn.juror.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.tencent.smtt.sdk.QbSdk
import com.unicorn.juror.BuildConfig
import me.yokeyword.fragmentation.Fragmentation
import net.danlew.android.joda.JodaTimeAndroid


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)

        // todo 稳定后开始清理 debug 用库
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)

        // todo 稳定后开始清理 debug 用库
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install()

        QbSdk.initX5Environment(applicationContext, null)
    }

}