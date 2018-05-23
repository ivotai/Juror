package com.unicorn.juror.personalBusiness.attendEvaluate.evaluation

import com.unicorn.juror.personalBusiness.attendEvaluate.evaluation.model.Option

class EvaluationOptionProvider {

    fun getNewOptions() = ArrayList<Option>().apply {
        add(Option(1, "司法礼仪", -1, ""))
        add(Option(2, "纪律审判", -1, ""))
        add(Option(3, "庭审驾驭", -1, ""))
        add(Option(4, "对陪审员引导能力", -1, ""))
        add(Option(5, "审判业务能力", -1, ""))
        add(Option(6, "廉洁自律", -1, ""))
    }

}