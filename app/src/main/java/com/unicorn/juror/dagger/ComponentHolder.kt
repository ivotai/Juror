package com.unicorn.juror.dagger


object ComponentHolder {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}