package com.unicorn.juror.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    @Provides
    @Named(value = "baseUrl")
//    fun baseUrl() = "http://154.0.21.123/juror/"

    fun baseUrl() = "http://154.0.66.244/juror/"
//    fun baseUrl() = "http://1.85.16.50:8083/juror/"

}


