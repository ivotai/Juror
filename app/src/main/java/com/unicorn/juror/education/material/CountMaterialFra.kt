package com.unicorn.juror.education.material

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
import com.unicorn.juror.education.court.Court
import com.unicorn.juror.education.model.Material
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fra_court_material.*


class CountMaterialFra : PageFra<Material>() {

    override val layoutID = R.layout.fra_court_material

    override val adapter1 = MaterialAdapter()

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<Material>>> {
        return ComponentHolder.appComponent.getLoginApi().getTeachingMaterialByFydm(page = page, rows = rows, fydm = fydm)
    }

    var fydm = AllTime.R00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    @Subscribe(tags = [Tag("onCourtSelect")])
    fun onCourtSelect(court: Court) {
        fydm = court.dm
        loadFirstPage()
    }


}
