package com.unicorn.juror.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    @Provides
    @Named(value = "baseUrl")
    fun baseUrl() = "https://kjgk.natapp4.cc/med/"

}