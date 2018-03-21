package com.unicorn.juror.registration

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseAct
import com.unicorn.juror.app.clicks
import com.unicorn.juror.app.default
import com.unicorn.juror.dagger.ComponentHolder
import com.unicorn.juror.registration.nation.Nation
import com.unicorn.juror.util.BottomSheetUtils
import com.unicorn.juror.util.DialogUtils
import com.unicorn.juror.util.Label
import kotlinx.android.synthetic.main.act_registration.*
import org.joda.time.DateTime
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
            BottomSheetUtils.show(this, "选择性别", listOf("男", "女"), object : BottomSheetUtils.PickerListener {
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
                        mzdm = o.value
                    }
                })
            }
        }
        tvBirthday.clicks().subscribe {
            val now = Calendar.getInstance()
            DatePickerDialog.newInstance(
                    { _, year, monthOfYear, dayOfMonth -> tvBirthday.text = DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0).toString("yyyy-MM-dd") },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)).show(supportFragmentManager, "dpd")

        }
        tvSubmit.clicks().subscribe {
            val list = listOf(
                    etName to "姓名", tvSex to "性别", etNativePlace to "籍贯", tvNation to "民族",
                    tvBirthday to "出生日期", etIdentityCard to "身份证号", etTelephone to "联系手机", etAddress to "住址"
            )
            var result = true
            for (pair in list) {
                if (TextUtils.isEmpty(pair.first.text)) {
                    ToastUtils.showShort("${pair.second}不能为空")
                    result = false
                    break
                }
            }
            if (!result) return@subscribe

            var mask: MaterialDialog? = null
            ComponentHolder.appComponent.getLoginApi()
                    .register(name = etName.text.toString(), sex = tvSex.text.toString(),
                            nativelyPlace = etNativePlace.text.toString(), mzdm = mzdm,
                            birthday = tvBirthday.text.toString(), identifyCard = etIdentityCard.text.toString(),
                            telephone = etTelephone.text.toString(), address = etAddress.text.toString()
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

    lateinit var mzdm: String

}
