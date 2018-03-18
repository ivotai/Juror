package com.unicorn.juror.app

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.tencent.smtt.sdk.QbSdk
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

        val cb = object : QbSdk.PreInitCallback {

            override fun onViewInitFinished(arg0: Boolean) {
                ToastUtils.showShort(arg0.toString())
            }

            override fun onCoreInitFinished() {
            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)

//        ZLAndroidApplication.init(this)
    }


//    companion object {
//        var bs: BookCollectionShadow? = null
//
//        fun initBook(context: Context, filePath: String, type: String) {
//            if (bs == null) {
//                bs = BookCollectionShadow()
//                bs?.bindToService(context, null)
//            }
//            val file = File(filePath)
//            if (file.exists()) {
//                val book = bs?.getBookByFile(filePath)
//                if (type.equals("TXT", ignoreCase = true) || type.equals("EPUB", ignoreCase = true)) {
//                    //跳转阅读器
//                    FBReader.openBookActivity(context, book, null)
//                } else if (type.equals("PDF", ignoreCase = true)) {
//                    //跳转PDF阅读器
//                    val uri = Uri.parse(filePath)
//                    val intent = Intent(context, MuPDFActivity::class.java)
//                    intent.action = Intent.ACTION_VIEW
//                    intent.data = uri
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    context.startActivity(intent)
//                }
//            }
//        }
//    }

}