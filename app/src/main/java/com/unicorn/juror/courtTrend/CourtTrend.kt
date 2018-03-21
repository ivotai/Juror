package com.unicorn.juror.courtTrend

import android.content.Context
import android.os.Environment
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.util.DialogUtils
import com.unicorn.juror.util.OpenFileUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.Serializable


data class CourtTrend(
        val id: String,
        val content: String,
        val fbtime: Long,
        val state: String,
        val title: String,
        val filename: String,
        val fileurl: String,
        val yfilename: String
) : Serializable {

    val attachment get() = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename)

    val attachmentPath get() = attachment.path

    val isAttachmentExist get() = attachment.exists()

    fun downloadAttachment(context: Context) {
        val mask = DialogUtils.showLoading(context, "下载附件中...")
        ComponentHolder.appComponent.getLoginApi().downAttachment(filename, fileurl, yfilename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            mask.dismiss()
                        },
                        onNext = {
                            mask.dismiss()
                            val inputSteam = it.byteStream()
                            val fos = FileOutputStream(attachment)
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
                            openAttachment(context)
                        }
                )
    }

    fun openAttachment(context: Context){
        OpenFileUtil.openFile(attachmentPath).apply {
            context.startActivity(this)
        }
    }

}