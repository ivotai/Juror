package com.unicorn.juror.app

data class Response<out T>(val flag: Boolean,val msg: String,val data: T)