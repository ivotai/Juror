package com.unicorn.juror.courtTrend.comment

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.*
import com.unicorn.juror.courtTrend.CourtTrend
import com.unicorn.juror.courtTrend.HeaderView
import com.unicorn.juror.dagger.ComponentHolder
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Observable
import kotlinx.android.synthetic.main.act_comment.*



class CommentAct : PageAct<Comment>() {
    override val adapter1 = CommentAdapter()
    override val recyclerView1: RecyclerView
        get() = recyclerView
    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override fun loadPage(page: Int, rows: Int): Observable<Response<Page<Comment>>> {
       return ComponentHolder.appComponent.getLoginApi()
                .getComment(page = page, rows = rows, courtTrendId = courtTrend.id)
    }

    override val layoutID = R.layout.act_comment

    private lateinit var courtTrend: CourtTrend


    private lateinit var headerView: HeaderView

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        super.initViews()
        courtTrend = intent.getSerializableExtra(Constant.COURT_TREND) as CourtTrend
        appBar.setTitle(courtTrend.title)
        appBar.showBackAction()

        headerView = HeaderView(this)
        headerView.tvContent.text = courtTrend.content
        headerView.tvAttachment.text = "附件: ${courtTrend.yfilename}"
        adapter1.setHeaderView(headerView)

        recyclerView1.addItemDecoration(
                HorizontalDividerItemDecoration.Builder(this)
                        .colorResId(R.color.md_grey_400)
                        .size(1)
                        .margin(ConvertUtils.dp2px(16f),ConvertUtils.dp2px(16f))
                        .build())

        GradientDrawable().apply {
            setStroke(1, ContextCompat.getColor(this@CommentAct, R.color.md_grey_300))
            setColor(ContextCompat.getColor(this@CommentAct, R.color.md_grey_200))
            cornerRadius = ConvertUtils.dp2px(20f).toFloat()
        }.let { etComment.background = it }

        if(AllTime.isVisitor){
            llComment.visibility = GONE
        }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        super.bindIntent()
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
                            ToastUtils.showShort(response.msg)
                            etComment.setText("")
                            etComment.clearFocus()
                            loadFirstPage()
                            recyclerView1.scrollToPosition(0)
                        }
                    }
                }
    }


}
