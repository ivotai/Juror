package com.unicorn.juror.personalBusiness.attendReceipt

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.Page
import com.unicorn.juror.app.PageFra
import com.unicorn.juror.app.Response
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.personalBusiness.model.Bs
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fra_court_material.*

class AttendReceiptFra : PageFra<Bs>() {

    override val layoutID = R.layout.fra_court_material

    override val adapter1 = AttendReceiptAdapter()

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<Bs>>> {
        return ComponentHolder.appComponent.getLoginApi().getAttendReceipt(page = page, rows = rows, appId = AllTime.userInfo.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    @Subscribe(tags = [(Tag("refreshAttendReceipt"))])
    fun refreshAttendReceipt(any:Any) {
        loadFirstPage()
    }

}