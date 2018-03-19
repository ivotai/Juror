package com.unicorn.juror.education.model

import android.os.Environment

class Book(val url: String, val name: String) {

    val path
        get()
        =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath + "/1.pdf"

}