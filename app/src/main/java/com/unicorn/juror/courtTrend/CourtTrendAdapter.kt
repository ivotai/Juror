package com.unicorn.juror.courtTrend

import android.os.Environment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.dagger.ComponentHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import java.io.File
import java.io.FileOutputStream

class CourtTrendAdapter : BaseQuickAdapter<CourtTrend, BaseViewHolder>(R.layout.item_trend) {
    override fun convert(helper: BaseViewHolder, item: CourtTrend) {
        item.apply {
            helper.setText(R.id.tvTitle, title)
            helper.setText(R.id.tvContent, content)
            helper.setText(R.id.tvFbTime, DateTime(fbtime).toString("yyyy-MM-dd"))

            helper.setOnClickListener(R.id.tvTitle, { download(item) })
        }
    }

    private fun download(item: CourtTrend) {
        ComponentHolder.appComponent.getLoginApi().downFile(item.filename, item.fileurl, item.yfilename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            it
                        },
                        onNext = {
                            it

                            val inputSteam = it.byteStream()
//                            val total = response.body()?.contentLength()
                            val pdf = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/" + item.yfilename)
                            val fos = FileOutputStream(pdf)
                            var sum = 0
                            var len = 0
                            val buf = ByteArray(2048)
                            while (true) {
                                len = inputSteam.read(buf)
                                if (len == -1) {
                                    break
                                }
                                fos.write(buf, 0, len)
                                sum += len
                            }
                            fos.flush()
                        }
                )
    }


}