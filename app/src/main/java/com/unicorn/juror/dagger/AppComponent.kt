package com.unicorn.juror.dagger

import com.unicorn.juror.login.api.LoginApi
import com.unicorn.juror.login.module.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ConfigModule::class, RetrofitModule::class, LoginModule::class])
interface AppComponent {

    fun getLoginApi(): LoginApi

}