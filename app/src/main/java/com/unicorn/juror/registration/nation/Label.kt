package com.unicorn.juror.registration.nation

data class Label(val text: String, val value: String) {
    override fun toString(): String {
        return text
    }
}