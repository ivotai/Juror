package com.unicorn.juror.bottomSheet

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.aigestudio.wheelpicker.WheelPicker
import com.unicorn.juror.R
import com.unicorn.juror.app.clicks

class BottomSheetUtils {

    interface PickerListener {
        fun onConfirm(o: Any)
    }

    companion object {

        @SuppressLint("InflateParams", "CheckResult")
        fun show(context: Context, title: String, data: List<Any>, listener: PickerListener) {
            val dialog = BottomSheetDialog(context)
            val view = LayoutInflater.from(context).inflate(R.layout.single_select, null)

            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = title
            val picker = view.findViewById<WheelPicker>(R.id.picker)
            picker.data = data

            val tvCancel = view.findViewById<TextView>(R.id.tvCancel)
            tvCancel.clicks().subscribe { dialog.dismiss() }
            val tvConfirm = view.findViewById<TextView>(R.id.tvConfirm)
            tvConfirm.clicks().subscribe {
                picker.data[picker.currentItemPosition].let { listener.onConfirm(it!!) }
                dialog.dismiss()
            }

            dialog.setContentView(view)
            dialog.show()
        }

    }

}