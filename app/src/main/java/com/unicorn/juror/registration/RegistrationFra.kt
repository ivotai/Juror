package com.unicorn.juror.registration

import android.os.Bundle
import android.view.View
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_registration.*

class RegistrationFra : BaseFra() {

    override val layoutID = R.layout.fra_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter  = RegistrationPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}