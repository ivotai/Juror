package com.unicorn.juror.main.trend


data class Trend(
		val id: String,
		val content: String,
		val fbtime: Long,
		val state: String,
		val title: String,
		val filename: String,
		val fileurl: String,
		val yfilename: Any
)