package com.unicorn.juror.registration

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.juror.R
import com.unicorn.juror.app.AllTime
import com.unicorn.juror.app.BaseFra
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.registration.model.PersonalInfo
import kotlinx.android.synthetic.main.fra_personal_info.*

class RegistrationFra : BaseFra() {

    override val layoutID = R.layout.fra_personal_info
    override fun initViews() {
    }

    override fun bindIntent() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val radii = ConvertUtils.dp2px(5f).toFloat()

        GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadius = radii
        }.let {
                    listOf(llState,llName, llSex, llNativePlace, llNation, llBirthday, llIdentityCard, llTelephone, llAddress)
                            .forEach { linearLayout -> linearLayout.background = it }
                }

        GradientDrawable().apply {
            setColor(Color.parseColor("#eeeeee"))
            setStroke(1, Color.parseColor("#d3d4d2"))
            cornerRadii = floatArrayOf(radii, radii, 0f, 0f, 0f, 0f, radii, radii)
        }.let {
                    listOf(lState,lName, lSex, lNativePlace, lNation, lBirthday, lIdentityCard, lTelephone, lAddress)
                            .forEach { textView -> textView.background = it }
                }

        appBar.setTitle("报名申请")

        queryPersonInfo()
    }

    @SuppressLint("CheckResult")
    private fun queryPersonInfo() {
        val api = ComponentHolder.appComponent.getLoginApi()
        val userInfo = AllTime.userInfo
        api.queryPersonalMessage(userInfo.id, userInfo.loginName)
                .default()
                .subscribe {
                    when {
                        it.isLoading() -> {
                        }
                        it.isError() -> {
                            ""
                        }
                        it.isSuccess() -> {
                            val response = it.response!!
//                            ToastUtils.showShort(response.msg)
                            render(response.data)
                        }
                    }
                }
    }

    private fun render(personalInfo: PersonalInfo) {
        tvState.text = personalInfo.state
        tvName.text = personalInfo.name
        tvSex.text = personalInfo.xb
        tvNativePlace.text = personalInfo.jg
        tvNation.text = personalInfo.mz
        tvBirthday.text = personalInfo.birthday.toString()
        tvIdentityCard.text = personalInfo.sfhm
        tvTelephone.text = personalInfo.lxsj
        tvAddress.text = personalInfo.address
    }


}