package com.unicorn.juror.education

import android.os.Environment
import android.util.Log
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
        helper.setOnClickListener(R.id.tvContent, { downloadBook(book = item) })
    }

    private fun downloadBook(book: Book) {
        val url = "http://dl.download.csdn.net/down11/20170911/2cbbb48574af169a48a9f4a9ecd7cc09.pdf?response-content-disposition=attachment%3Bfilename%3D%22RXJava%E4%B8%AD%E6%96%87%E5%B8%AE%E5%8A%A9%E6%96%87%E6%A1%A3.pdf%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1521430633&Signature=lVpES8Sxt5PUrDIbsuIGd5OWyVA%3D&user=ivolianer&sourceid=9973554&sourcescore=10&isvip=1&ivolianer&9973554"

//        val url = "http://154.0.66.244/juror/app/tzfjdownload?filename=B3BA9AE4DD41472F83D3A183CCFD7633_1.pdf&fileurl=/homefile/20180319/&xsmc=2016"
        val request = Request.Builder().url(url).build()
        ComponentHolder.appComponent.getClient()
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        Log.e("","")
                        ""
                    }

                    override fun onResponse(call: Call?, response: Response) {
                        val inputSteam = response.body()!!.byteStream()
                        val total = response.body()?.contentLength()
                        val pdf = File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/1.pdf")
                        val fos = FileOutputStream(pdf)
                        var sum = 0
                        var len = 0
                        val buf = ByteArray(2048)
                        while (true) {
                            len = inputSteam.read(buf)
                            if (len == -1) {
                                break
                            }
                            fos.write(buf, 0, len)
                            sum += len
                        }
                        fos.flush()
                    }
                })
    }

}