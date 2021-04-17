package dev.remylavergne.ktoggl.report.common

object SummaryParams {

    fun grouping(data: ParamSummaryGrouping.By = ParamSummaryGrouping.By.PROJECTS): Param<ParamSummaryGrouping.By> =
        ParamSummaryGrouping(key = "grouping", value = data)

    fun subgrouping(data: ParamSummarySubgrouping.By = ParamSummarySubgrouping.By.TIME_ENTRIES): Param<ParamSummarySubgrouping.By> =
        ParamSummarySubgrouping(key = "subgrouping", value = data)
}