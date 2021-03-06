package com.unicorn.juror.app

import android.annotation.SuppressLint
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class PageFra<T> : BaseFra() {

    abstract val adapter1: BaseQuickAdapter<T, BaseViewHolder>

    abstract val recyclerView1: RecyclerView

    abstract val swipeRefreshLayout1: SwipeRefreshLayout

    abstract fun loadPage(page: Int, rows: Int): Observable<Response<Page<T>>>

    private val rows = 5

    private val pageNo
        get() = adapter1.data.size / rows

    private val compositeDisposable = CompositeDisposable()

    override fun initViews() {
        swipeRefreshLayout1.setOnRefreshListener { loadFirstPage() }
        swipeRefreshLayout1.setColorSchemeResources(R.color.colorPrimary)
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(context)
            adapter1.bindToRecyclerView(this)
            adapter1.setEnableLoadMore(true)
            adapter1.setOnLoadMoreListener({ loadNextPage() }, recyclerView1)
//            adapter1.setLoadMoreView(SimpleLoadMoreView())
//            adapter1.setLoadMoreView(CustomLoadMoreView())
        }
    }

    override fun bindIntent() {
        loadFirstPage()
    }

    @SuppressLint("CheckResult")
    fun loadFirstPage() {
        adapter1.data.clear()
        loadPage(page = pageNo, rows = rows)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                            swipeRefreshLayout1.isRefreshing = true
                        }
                        it.isError() -> {
                            swipeRefreshLayout1.isRefreshing = false
                        }
                        it.isSuccess() -> {
                            swipeRefreshLayout1.isRefreshing = false
                            val response = it.response!!
//                            ToastUtils.showShort(response.msg)
                            adapter1.setNewData(response.data.rows)
                            if (adapter1.data.size == response.data.total) {
                                adapter1.loadMoreEnd()
                            }
                        }
                    }
                }.let { compositeDisposable.add(it)  }
    }

    @SuppressLint("CheckResult")
    private fun loadNextPage() {
        loadPage(page = pageNo, rows = rows)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                        }
                        it.isError() -> {
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
//                            ToastUtils.showShort(response.msg)
                            adapter1.loadMoreComplete()
                            adapter1.addData(response.data.rows)
                            adapter1.notifyDataSetChanged()
                            if (adapter1.data.size == response.data.total) {
                                adapter1.loadMoreEnd()
                            }
                        }
                    }
                }.let { compositeDisposable.add(it)  }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}