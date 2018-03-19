package com.unicorn.juror.education.model


data class Material(
        val id: String,
        val pulc_time: Long,
        val img_name: String,
        val imgpath: String,
        val title: String,
        val content: String,
        val save_name: String,
        val path: String,
        val file_name: String,
        val downloads: Long
)