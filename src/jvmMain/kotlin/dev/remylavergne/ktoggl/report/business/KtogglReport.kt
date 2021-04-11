package dev.remylavergne.ktoggl.report.business

import dev.remylavergne.ktoggl.BASE_URL
import dev.remylavergne.ktoggl.duplicate
import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.report.KtogglReportApi
import dev.remylavergne.ktoggl.report.common.WeeklyParams
import dev.remylavergne.ktoggl.report.common.Param
import dev.remylavergne.ktoggl.report.common.ParamCalculate
import dev.remylavergne.ktoggl.report.common.ParamGrouping
import dev.remylavergne.ktoggl.report.common.Params
import dev.remylavergne.ktoggl.report.models.*
import dev.remylavergne.ktoggl.toQueryParams
import io.ktor.client.*
import io.ktor.client.request.*


typealias WeeklyProjectsTimeResult = BaseTime<List<WeeklyProjectsTime>>
typealias WeeklyProjectsEarningsResult = BaseEarnings<List<WeeklyProjectsEarnings>>
typealias WeeklyUsersTimeResult = BaseTime<List<WeeklyUsersTime>>
typealias WeeklyUsersEarningsResult = BaseEarnings<List<WeeklyUsersEarnings>>


data class KtogglReport(
    private val account: ApiCredentials,
) : KtogglReportApi {

    override suspend fun weeklyProjectsTime(block: Params.() -> Unit): ApiResult<WeeklyProjectsTimeResult> {
        val params = Params().apply(block).get()
        return this.getWeekly(*params, WeeklyParams.grouping(), WeeklyParams.calculate())
    }

    override suspend fun weeklyProjectsEarnings(block: Params.() -> Unit): ApiResult<WeeklyProjectsEarningsResult> {
        val params = Params().apply(block).get()
        return this.getWeekly(
            *params,
            WeeklyParams.grouping(),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS)
        )
    }

    override suspend fun weeklyUsersTime(block: Params.() -> Unit): ApiResult<WeeklyUsersTimeResult> {
        val params = Params().apply(block).get()
        return this.getWeekly(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate()
        )
    }

    override suspend fun weeklyUsersEarnings(block: Params.() -> Unit): ApiResult<WeeklyUsersEarningsResult> {
        val params = Params().apply(block).get()
        return this.getWeekly(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS)
        )
    }

    private suspend inline fun <reified T> getWeekly(vararg params: Param<*> = arrayOf()): ApiResult<T> {
        if (params.duplicate()) {
            return ApiResult.Error(message = "Varargs contains duplicate values")
        }

        val queryParams: String = arrayOf(*params).toQueryParams()

        val result: T =
            account.httpClient.use { client: HttpClient -> client.get<T>("$BASE_URL/weekly$queryParams") }

        return ApiResult.Success(result)
    }
}