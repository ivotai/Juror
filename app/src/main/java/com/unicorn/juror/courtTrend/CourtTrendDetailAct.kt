package com.unicorn.juror.courtTrend

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.*
import com.unicorn.juror.dagger.ComponentHolder
import kotlinx.android.synthetic.main.act_court_trend_detail.*

class CourtTrendDetailAct : BaseAct() {

    override val layoutID = R.layout.act_court_trend_detail

    private lateinit var courtTrend: CourtTrend

    // todo
    val courtTrendAdapter = CourtTrendAdapter()

    private lateinit var headerView: HeaderView

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

        GradientDrawable().apply {
            setStroke(1, ContextCompat.getColor(this@CourtTrendDetailAct, R.color.md_grey_300))
            setColor(ContextCompat.getColor(this@CourtTrendDetailAct, R.color.md_grey_200))
            cornerRadius = ConvertUtils.dp2px(20f).toFloat()
        }.let { etComment.background = it }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        headerView.tvAttachment.clicks().subscribe {
            openAttachment(courtTrend)
        }
        itvSend.clicks().subscribe {
            addComment(etComment.text.toString())
        }
    }

    private fun openAttachment(item: CourtTrend) {
        if (item.isAttachmentExist) item.openAttachment(this)
        else item.downloadAttachment(this)
    }

    @SuppressLint("CheckResult")
    private fun addComment(content: String) {
//        var mask: MaterialDialog? = null
        ComponentHolder.appComponent.getLoginApi()
                .addComment(appId = AllTime.userInfo.id, courtTrendId = courtTrend.id, content = content)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
//                            mask = DialogUtils.showLoading(context = this, title = "提交评论中...")
                        }
                        it.isError() -> {
                            it
//                            mask?.dismiss()
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
                            if (!response.flag) {
//                                ToastUtils.showShort(response.msg)
                            }
                        }
                    }
                }
    }


}
