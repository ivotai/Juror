package com.unicorn.juror.registration

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.bottomSheet.BottomSheetUtils
import com.unicorn.juror.registration.nation.Label
import com.unicorn.juror.registration.nation.Nation
import kotlinx.android.synthetic.main.act_registration.*

class RegistrationAct : BaseAct() {

    override val layoutID = R.layout.act_registration

    override fun initViews() {
        appBar.setTitle("个人报名申请")
        appBar.showBackAction()

        val radii = ConvertUtils.dp2px(5f).toFloat()

        GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadius = radii
        }.let {
                    listOf(llName, llSex, llNativePlace, llNation, llBirthday, llIdentityCard, llTelephone, llAddress)
                            .forEach { linearLayout -> linearLayout.background = it }
                }

        GradientDrawable().apply {
            setColor(Color.parseColor("#eeeeee"))
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadii = floatArrayOf(radii, radii, 0f, 0f, 0f, 0f, radii, radii)
        }.let {
                    listOf(lName, lSex, lNativePlace, lNation, lBirthday, lIdentityCard, lTelephone, lAddress)
                            .forEach { textView -> textView.background = it }
                }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        tvSex.clicks().subscribe {
            BottomSheetUtils.show(this, "选择婚姻状况", listOf("已婚", "未婚"), object : BottomSheetUtils.PickerListener {
                override fun onConfirm(o: Any) {
                    tvSex.text = o as String
                }
            })
        }
        tvNation.clicks().subscribe {
            Nation.list().let {
                BottomSheetUtils.show(this, "选择民族", it, object : BottomSheetUtils.PickerListener {
                    override fun onConfirm(o: Any) {
                        tvNation.text = (o as Label).text
                    }
                })
            }
        }
    }

}
