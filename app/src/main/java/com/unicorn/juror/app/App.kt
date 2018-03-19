package com.unicorn.juror.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.unicorn.juror.BuildConfig
import me.yokeyword.fragmentation.Fragmentation
import net.danlew.android.joda.JodaTimeAndroid


class App :Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install()

//         val cb = object : QbSdk.PreInitCallback {
//
//            override fun onViewInitFinished(arg0: Boolean) {
//                ToastUtils.showShort(arg0.toString())
//            }
//
//            override fun onCoreInitFinished() {
//            }
//        }
//        //x5内核初始化接口
//        QbSdk.initX5Environment(applicationContext,cb)
    }



}