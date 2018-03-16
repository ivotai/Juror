package com.unicorn.juror.registration

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.bottomSheet.BottomSheetUtils
import com.unicorn.juror.bottomSheet.DialogUtils
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.registration.nation.Label
import com.unicorn.juror.registration.nation.Nation
import kotlinx.android.synthetic.main.act_registration.*
import java.util.*

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

        etTelephone.setText("13611840424")
        etIdentityCard.setText("310109199101190576")
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
                        nation = o
                    }
                })
            }
        }
        // todo
        tvBirthday.clicks().subscribe {
            val now = Calendar.getInstance()
            DatePickerDialog.newInstance(
                    { dialog, year, monthOfYear, dayOfMonth ->
                        birthday = "$year-$monthOfYear-$dayOfMonth"
                        tvBirthday.text = birthday
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)).show(supportFragmentManager, "dpd")

        }
        tvSubmit.clicks().subscribe {
            var mask: MaterialDialog? = null
            ComponentHolder.appComponent.getLoginApi()
                    .register(name = etName.text.toString(),sex = tvSex.text.toString(),
                            nativelyPlace = etNativePlace.text.toString(),nation = nation!!.value,
                            birthday = tvBirthday.text.toString(),identifyCard = etIdentityCard.text.toString(),
                            telephone = etTelephone.text.toString(),  address = etAddress.text.toString()
                    )
                    .default()
                    .subscribe {
                        when {
                            it.isLoading() -> {
                                mask = DialogUtils.showLoading(context = this, title = "提交中...")
                            }
                            it.isError() -> {
                                mask?.dismiss()
                            }
                            it.isSuccess() -> {
                                mask?.dismiss()
                                val response = it.response!!
                                if (!response.flag) {
                                    ToastUtils.showShort(response.msg)
                                } else {
//                                    AllTime.userInfo = response.data
//                                    Intent(this, MainAct::class.java).apply {
//                                        startActivity(this)
//                                    }
                                }
                            }
                        }
                    }
        }
    }

    var nation: Label? = null
    var birthday: String? = null
}
