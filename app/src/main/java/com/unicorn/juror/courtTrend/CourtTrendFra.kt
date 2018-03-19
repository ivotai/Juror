package com.unicorn.juror.courtTrend

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import kotlinx.android.synthetic.main.fra_trend.*

class CourtTrendFra : BaseFra() {

    override val layoutID = R.layout.fra_trend

    private val trendAdapter = CourtTrendAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        appBar.setTitle("资讯热点")

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            trendAdapter.bindToRecyclerView(this)
            trendAdapter.setOnLoadMoreListener({ loadMore()}, recyclerView)
        }

        loadFirst()
    }

    val rows = 5
    override fun initViews() {
    }

    override fun bindIntent() {
    }

    @SuppressLint("CheckResult")
    private fun loadFirst() {
        ComponentHolder.appComponent.getLoginApi()
                .getCourtTrend(page = pageNo, rows = rows)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                        }
                        it.isError() -> {
//                            mask?.dismiss()
//                            Intent(this, MainAct::class.java).apply {
//                                startActivity(this)
//                            }
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
                            ToastUtils.showShort(response.msg)
                            trendAdapter.setNewData(response.data.rows)

                            if (trendAdapter.data.size == response.data.total){
                                trendAdapter.loadMoreEnd()
                            }
                        }
                    }
                }
    }

    private val pageNo
        get() = trendAdapter.data.size / rows

    @SuppressLint("CheckResult")
    private fun loadMore() {
        ComponentHolder.appComponent.getLoginApi()
                .getCourtTrend(page = pageNo, rows = rows)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                        }
                        it.isError() -> {
//                            mask?.dismiss()
//                            Intent(this, MainAct::class.java).apply {
//                                startActivity(this)
//                            }
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
                            ToastUtils.showShort(response.msg)

                            trendAdapter.loadMoreComplete()
                            trendAdapter.addData(response.data.rows)
                            trendAdapter.notifyDataSetChanged()
                            if (trendAdapter.data.size == response.data.total){
                                trendAdapter.loadMoreEnd()
                            }
                        }
                    }
                }


    }


}