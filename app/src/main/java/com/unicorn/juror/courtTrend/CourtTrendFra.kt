package com.unicorn.juror.courtTrend

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.juror.R
import com.unicorn.juror.app.Page
import com.unicorn.juror.app.PageFra
import com.unicorn.juror.app.Response
import com.unicorn.juror.dagger.ComponentHolder
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fra_court_trend.*

class CourtTrendFra : PageFra<CourtTrend>() {

    override val layoutID = R.layout.fra_court_trend

    override val adapter1 = CourtTrendAdapter()

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<CourtTrend>>> {
        return ComponentHolder.appComponent.getLoginApi().getCourtTrend(page, rows)
    }

    override fun initViews() {
        appBar.setTitle("法院动态")
        super.initViews()
    }

}