package com.unicorn.juror.app


data class ViewState<out T>(
        val response: T? = null,
        val error: Throwable? = null
) {

    fun isLoading() = response == null && error == null
    fun isSuccess() = response != null
    fun isError() = error != null

}