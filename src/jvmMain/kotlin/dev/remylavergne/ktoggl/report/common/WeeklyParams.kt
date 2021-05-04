package dev.remylavergne.ktoggl.report.common

import dev.remylavergne.ktoggl.report.common.Param
import dev.remylavergne.ktoggl.report.common.ParamCalculate
import dev.remylavergne.ktoggl.report.common.ParamGrouping

object WeeklyParams: Params() {
    fun grouping(data: ParamGrouping.Grouping = ParamGrouping.Grouping.PROJECTS): Param<ParamGrouping.Grouping> =
        ParamGrouping(key = "grouping", value = data)

    fun calculate(data: ParamCalculate.Calculate = ParamCalculate.Calculate.TIME): Param<ParamCalculate.Calculate> =
        ParamCalculate(key = "calculate", value = data)
}
