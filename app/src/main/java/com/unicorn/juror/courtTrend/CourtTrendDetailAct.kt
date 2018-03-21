package com.unicorn.juror.courtTrend

import android.annotation.SuppressLint
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.Constant
import com.unicorn.juror.app.clicks
import com.unicorn.juror.bottomSheet.DialogUtils
import com.unicorn.juror.dagger.ComponentHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_court_trend_detail.*
import java.io.File
import java.io.FileOutputStream

class CourtTrendDetailAct : BaseAct() {

    override val layoutID = R.layout.act_court_trend_detail

    private lateinit var courtTrend: CourtTrend

    // todo
    val courtTrendAdapter = CourtTrendAdapter()

    lateinit var headerView: HeaderView

    override fun initViews() {
        courtTrend = intent.getSerializableExtra(Constant.COURT_TREND) as CourtTrend
        appBar.setTitle(courtTrend.title)
        appBar.showBackAction()

        recyclerView.layoutManager = LinearLayoutManager(this)
        headerView = HeaderView(this)
        headerView.tvContent.text = courtTrend.content
        headerView.tvAttachment.text = courtTrend.yfilename
        courtTrendAdapter.setHeaderView(headerView)
        recyclerView.adapter = courtTrendAdapter
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        headerView.tvAttachment.clicks().subscribe {
            download(courtTrend)
        }
    }

    private fun download(item: CourtTrend) {
        val mask = DialogUtils.showLoading(this, "下载附件中...")
        ComponentHolder.appComponent.getLoginApi().downFile(item.filename, item.fileurl, item.yfilename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            mask.dismiss()
                        },
                        onNext = {
                            mask.dismiss()
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
