package com.unicorn.juror.app


data class Court(
        val dm: String,
        val dmms: String,
        val sjdm: String,
        var children: List<Court> = ArrayList()
)