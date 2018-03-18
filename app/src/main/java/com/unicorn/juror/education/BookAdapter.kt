package com.unicorn.juror.education

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.education.model.Book
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BookAdapter : BaseQuickAdapter<Book, BaseViewHolder>(R.layout.item_book) {

    override fun convert(helper: BaseViewHolder, item: Book) {
        helper.setText(R.id.tvTitle, item.name)
        helper.setOnClickListener(R.id.tvTitle, { downloadBook(book = item) })
    }

    private fun downloadBook(book: Book) {
        val url = book.url
        val request = Request.Builder().url(url).build()
        ComponentHolder.appComponent.getClient()
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                    }

                    override fun onResponse(call: Call?, response: Response) {
                        val inputSteam = response.body()!!.byteStream()
                        val total = response.body()?.contentLength()
                        val pdf = File(book.path)
                        val fos = FileOutputStream(pdf)
                        var sum  = 0
                        var len = 0
                        val buf = ByteArray(2048)
                        while (true){
                            len = inputSteam.read(buf)
                            if (len == -1){
                                break
                            }
                            fos.write(buf,0,len)
                            sum += len
                        }
                        fos.flush()
                    }
                })
    }

}