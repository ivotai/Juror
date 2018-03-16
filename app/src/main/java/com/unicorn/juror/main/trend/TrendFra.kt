package com.unicorn.juror.main.trend

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

class TrendFra : BaseFra() {

    override val layoutID = R.layout.fra_trend

    val trendAdapter = TrendAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        appBar.setTitle("资讯热点")

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            trendAdapter.bindToRecyclerView(this)
        }

        getNews()
    }

    @SuppressLint("CheckResult")
    private fun getNews() {
        ComponentHolder.appComponent.getLoginApi()

                .getNews(fydm = "", page = 0, rows = 10)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
//                            mask = DialogUtils.showLoading(context = this, title = "登录中...")
                        }
                        it.isError() -> {
                            ""
//                            mask?.dismiss()
//                            Intent(this, MainAct::class.java).apply {
//                                startActivity(this)
//                            }
                        }
                        it.isSuccess() -> {
//                            mask?.dismiss()
                            val response = it.response!!
                            ToastUtils.showShort(response.msg)
                            trendAdapter.setNewData(response.data.rows)
                        }
                    }
                }


    }

}