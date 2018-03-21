package com.unicorn.juror.courtTrend

import java.io.Serializable


data class CourtTrend(
        val id: String,
        val content: String,
        val fbtime: Long,
        val state: String,
        val title: String,
        val filename: String,
        val fileurl: String,
        val yfilename: String
) : Serializable