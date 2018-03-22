package com.unicorn.juror.education.model

import android.content.Context
import android.os.Environment
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.tbs.FileDisplayActivity
import com.unicorn.juror.util.DialogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream


data class Material(
        val id: String,
        val pulc_time: Long,
        val img_name: String,
        val imgpath: String,
        val title: String,
        val content: String,
        val save_name: String,
        val path: String,
        val file_name: String,
        val downloads: Long
) {

    val file get() = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), save_name)

    val isExist get() = file.exists()

    fun download(context: Context) {
        val mask = DialogUtils.showLoading(context, "下载材料中...")
        val appId = if (AllTime.isVisitor) "" else AllTime.userInfo.id
        ComponentHolder.appComponent.getLoginApi().downloadMaterial(save_name, path, file_name, appid = appId, trainingid = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            mask.dismiss()
                        },
                        onNext = {
                            mask.dismiss()
                            val inputSteam = it.byteStream()
                            val fos = FileOutputStream(file)
                            var len: Int
                            val buf = ByteArray(2048)
                            while (true) {
                                len = inputSteam.read(buf)
                                if (len == -1) {
                                    break
                                }
                                fos.write(buf, 0, len)
                            }
                            fos.flush()
                            open(context)
                        }
                )
    }

    fun open(context: Context) {
        FileDisplayActivity.show(context, file.path, id)
    }

}