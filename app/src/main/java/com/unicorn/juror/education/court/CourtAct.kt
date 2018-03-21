package com.unicorn.juror.education.court

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import kotlinx.android.synthetic.main.act_court.*

class CourtAct : BaseAct() {

    override val layoutID = R.layout.act_court

    override fun initViews() {
        appBar.setTitle("选择法院")
        appBar.showBackAction()
        initRecyclerView()
    }

    val courtAdapter = CourtAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            courtAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        getCourt()
    }

    @SuppressLint("CheckResult")
    private fun getCourt() {
        ComponentHolder.appComponent.getLoginApi().getCourt().default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                        }
                        it.isError() -> {
                            it
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
//                            ToastUtils.showShort(response.msg)
                            cope(response.data)
                        }
                    }
                }
    }

    private fun cope(courts: List<Court>) {

        val r00: Court? = courts.lastOrNull { it.dm == AllTime.R00 }
        r00!!.courtLevel = 0
        r00.children = ArrayList()
        courts.forEach { if (it.sjdm == r00.dm) r00.children.add(it) }

        for (r2 in r00.children) {
            r2.courtLevel = 1
            r2.children = ArrayList()
            courts.forEach {
                if (it.sjdm == r2.dm) {
                    r2.children.add(it)
                    it.courtLevel = 2
                    it.children = ArrayList()
                }
            }
        }
        val list = ArrayList<Court>()
        list.add(r00)
        courtAdapter.setNewData(list)
//        courtAdapter.expand(0)
    }


}
