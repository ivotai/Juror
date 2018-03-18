package com.unicorn.juror.education

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.education.model.Book

class BookAdapter : BaseQuickAdapter<Book, BaseViewHolder>(R.layout.item_book) {

    override fun convert(helper: BaseViewHolder, item: Book) {
        helper.setText(R.id.tvTitle, item.name)
        helper.setOnClickListener(R.id.tvTitle, { downloadBook(book = item) })
    }

    private fun downloadBook(book: Book) {

    }

}