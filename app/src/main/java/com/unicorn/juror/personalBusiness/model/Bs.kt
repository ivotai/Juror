package com.unicorn.juror.personalBusiness.model


data class Bs(
		val state: Int,
		val ajbs: String,
		val cxrs: Int,
		val id: String,
		val sqrmc: String,	// 申请人
		val cxtime: Long,	// 抽选时间
		val sqtime: Long,	// 申请时间
		val cbrmc: String,	// 承办人
		val ahqc: String	// 案号全称
)