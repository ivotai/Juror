package com.unicorn.juror.registration

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_personal_info.*

class PersonalInfoFra : BaseFra() {

    override val layoutID = R.layout.fra_personal_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val radii = ConvertUtils.dp2px(5f).toFloat()

        GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadius = radii
        }.let {
                    listOf(llName, llSex, llAge, llBirthday, llNativePlace, llNation, llIdentityCard, llMaritalStatus, llTelephone)
                            .forEach { linearLayout -> linearLayout.background = it }
                }

        GradientDrawable().apply {
            setColor(Color.parseColor("#eeeeee"))
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadii = floatArrayOf(radii, radii, 0f, 0f, 0f, 0f, radii, radii)
        }.let {
                    listOf(tvName, tvSex, tvAge, tvBirthday, tvNativePlace, tvNation, tvIdentityCard, tvMaritalStatus, tvTelephone)
                            .forEach { textView -> textView.background = it }
                }

    }

}