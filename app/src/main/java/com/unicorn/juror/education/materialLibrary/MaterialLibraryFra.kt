package com.unicorn.juror.education.materialLibrary

import android.support.v7.widget.GridLayoutManager
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.education.BookAdapter
import com.unicorn.juror.education.model.Book
import kotlinx.android.synthetic.main.fra_material_library.*


class MaterialLibraryFra : BaseFra() {

    override val layoutID = R.layout.fra_material_library

    private val bookAdapter = BookAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            bookAdapter.bindToRecyclerView(this)
        }
        val url = ""
        bookAdapter.setNewData(listOf(
                Book("", "测试书籍"),
                Book("", "测试书籍"),
                Book("", "测试书籍"),
                Book("", "测试书籍")
        ))
    }

    override fun bindIntent() {
    }

}
