package com.unicorn.juror.education.teachingMaterial

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.juror.R
import com.unicorn.juror.app.Page
import com.unicorn.juror.app.PageFra
import com.unicorn.juror.app.Response
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.education.MaterialAdapter
import com.unicorn.juror.education.model.Material
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fra_teaching_material.*


class TeachingMaterialFra : PageFra<Material>() {

    override val layoutID = R.layout.fra_teaching_material

    override val adapter1 = MaterialAdapter()

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<Material>>> {
        return ComponentHolder.appComponent.getLoginApi().getTeachingMaterialByFydm(page = page, rows = rows, fydm = "R00")
    }

}
