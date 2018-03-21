package com.unicorn.juror.util

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

class DialogUtils {

    companion object {

        fun showLoading(context: Context, title: String, content: String="请稍后"): MaterialDialog {
            return MaterialDialog.Builder(context)
                    .title(title)
                    .content(content)
                    .progress(true, 0)
                    .show()
        }

    }

}