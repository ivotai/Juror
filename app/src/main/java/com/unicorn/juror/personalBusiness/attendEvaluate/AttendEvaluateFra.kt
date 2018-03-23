package com.unicorn.juror.personalBusiness.attendEvaluate

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.Page
import com.unicorn.juror.app.PageFra
import com.unicorn.juror.app.Response
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.personalBusiness.model.Bs2
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fra_court_material.*

class AttendEvaluateFra : PageFra<Bs2>() {

    override val layoutID = R.layout.fra_court_material

    override val adapter1 = AttendEvaluateAdapter()

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<Bs2>>> {
        return ComponentHolder.appComponent.getLoginApi().getAttendEvaluate(page = page, rows = rows, appId = AllTime.userInfo.id)
    }

}