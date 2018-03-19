package com.unicorn.juror.education

import android.os.Environment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.juror.R
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.education.model.Material
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class MaterialAdapter : BaseQuickAdapter<Material, BaseViewHolder>(R.layout.item_book) {

    override fun convert(helper: BaseViewHolder, item: Material) {
        helper.setText(R.id.tvTitle, item.id)
        helper.setOnClickListener(R.id.tvContent, { downloadBook(item) })
    }

    private fun downloadBook(item: Material) {
        ComponentHolder.appComponent.getLoginApi().downloadMaterial(item.save_name, item.path, item.file_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            it
                        },
                        onNext = {
                            it

                            val inputSteam = it.byteStream()
//                            val total = response.body()?.contentLength()
                            val pdf = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/" + item.file_name)
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
                )

    }

}