package dev.remylavergne.ktoggl.report

import dev.remylavergne.ktoggl.report.business.*
import dev.remylavergne.ktoggl.report.common.Params
import dev.remylavergne.ktoggl.report.models.ApiCredentials
import dev.remylavergne.ktoggl.report.service.ApiResult


interface KtogglReportApi {
    suspend fun weeklyProjectsTime(block: Params.() -> Unit): ApiResult<WeeklyProjectsTimeResult>
    suspend fun weeklyProjectsEarnings(block: Params.() -> Unit): ApiResult<WeeklyProjectsEarningsResult>
    suspend fun weeklyUsersTime(block: Params.() -> Unit): ApiResult<WeeklyUsersTimeResult>
    suspend fun weeklyUsersEarnings(block: Params.() -> Unit): ApiResult<WeeklyUsersEarningsResult>
}

inline fun KtogglReportApi(crossinline block: ApiCredentialsBuilderDsl.() -> Unit): KtogglReportApi {
    val apiCredentialsBuilder: ApiCredentials = ApiCredentialsBuilderDsl().apply(block).build()
    return KtogglReport(apiCredentialsBuilder)
}
