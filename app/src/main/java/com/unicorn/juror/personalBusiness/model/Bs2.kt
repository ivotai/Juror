package com.unicorn.juror.personalBusiness.model

data class Bs2(
		val fydm: String,
		val ahqc: String,	// 案号
		val ajbs: String,
		val laaymc: String,	// 案由
		val larq: String,	// 时间
		val cbrmc: String,	// 承办人
		val ftmc: String,	// 法庭名称
		val fymc: String,
		val kssj: Long,		// 开始时间
		val jssj: Long,		// 结束时间
		val ispy: Int,
		val isssrd: Int
)