package com.unicorn.juror.registration

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
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
                    listOf(llName, llSex, llNativePlace, llNation,llBirthday, llIdentityCard, llTelephone,llAddress)
                            .forEach { linearLayout -> linearLayout.background = it }
                }

        GradientDrawable().apply {
            setColor(Color.parseColor("#eeeeee"))
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadii = floatArrayOf(radii, radii, 0f, 0f, 0f, 0f, radii, radii)
        }.let {
                    listOf(tvName, tvSex, tvNativePlace, tvNation, tvBirthday,tvIdentityCard, tvTelephone,tvAddress)
                            .forEach { textView -> textView.background = it }
                }
    }

    override fun bindIntent() {
        //
    }

}
