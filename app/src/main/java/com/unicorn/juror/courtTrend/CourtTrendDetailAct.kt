package com.unicorn.juror.courtTrend

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.Constant
import com.unicorn.juror.app.clicks
import kotlinx.android.synthetic.main.act_court_trend_detail.*

class CourtTrendDetailAct : BaseAct() {

    override val layoutID = R.layout.act_court_trend_detail

    private lateinit var courtTrend: CourtTrend

    // todo
    val courtTrendAdapter = CourtTrendAdapter()

    lateinit var headerView: HeaderView

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        courtTrend = intent.getSerializableExtra(Constant.COURT_TREND) as CourtTrend
        appBar.setTitle(courtTrend.title)
        appBar.showBackAction()

        recyclerView.layoutManager = LinearLayoutManager(this)
        headerView = HeaderView(this)
        headerView.tvContent.text = courtTrend.content
        headerView.tvAttachment.text = "附件: ${courtTrend.yfilename}"
        courtTrendAdapter.setHeaderView(headerView)
        recyclerView.adapter = courtTrendAdapter
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        headerView.tvAttachment.clicks().subscribe {
            openAttachment(courtTrend)
        }
    }

    private fun openAttachment(item: CourtTrend) {
        if (item.isAttachmentExist) item.openAttachment(this)
        else item.downloadAttachment(this)
    }

}
