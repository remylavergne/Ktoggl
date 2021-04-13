package dev.remylavergne.ktoggl.report.business

import dev.remylavergne.ktoggl.BASE_URL
import dev.remylavergne.ktoggl.duplicate
import dev.remylavergne.ktoggl.report.common.*
import dev.remylavergne.ktoggl.report.models.*
import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.toQueryParams
import io.ktor.client.*
import io.ktor.client.request.*


typealias WeeklyProjectsTimeResult = BaseTime<List<WeeklyProjectsTime>>
typealias WeeklyProjectsEarningsResult = BaseEarnings<List<WeeklyProjectsEarnings>>
typealias WeeklyUsersTimeResult = BaseTime<List<WeeklyUsersTime>>
typealias WeeklyUsersEarningsResult = BaseEarnings<List<WeeklyUsersEarnings>>


data class KtogglReport(
    private val account: ApiCredentials,
) {

    suspend fun weeklyProjectsTime(block: Params.() -> Unit): ApiResult<WeeklyProjectsTimeResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params, WeeklyParams.grouping(), WeeklyParams.calculate(),
            endpoint = Endpoint.WEEKLY,
        )
    }

    suspend fun weeklyProjectsEarnings(block: Params.() -> Unit): ApiResult<WeeklyProjectsEarningsResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS),
            endpoint = Endpoint.WEEKLY,
        )
    }

    suspend fun weeklyUsersTime(block: Params.() -> Unit): ApiResult<WeeklyUsersTimeResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate(),
            endpoint = Endpoint.WEEKLY,
        )
    }

    suspend fun weeklyUsersEarnings(block: Params.() -> Unit): ApiResult<WeeklyUsersEarningsResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS),
            endpoint = Endpoint.WEEKLY,
        )
    }

    suspend fun details(page: Int = 1, block: Params.() -> Unit): ApiResult<BaseDetailed> {
        if (page <= 0) {
            throw Exception("Minimum page must be 1")
        }
        val params = Params().apply(block).get()
        val pageParam: Param<Int> = DetailedParams.page(page)
        println(page)
        return this.makeApiCall(*params, pageParam, endpoint = Endpoint.DETAILED)
    }

    private suspend inline fun <reified T> makeApiCall(
        vararg params: Param<*> = arrayOf(),
        endpoint: Endpoint
    ): ApiResult<T> {
        if (params.duplicate()) {
            return ApiResult.Error(message = "Some query params are duplicated")
        }

        val queryParams: String = arrayOf(*params).toQueryParams()

        val result: T =
            account.httpClient.use { client: HttpClient -> client.get<T>("$BASE_URL${endpoint.v}$queryParams") }

        return ApiResult.Success(result)
    }
}

enum class Endpoint(val v: String) {
    WEEKLY("/weekly"),
    DETAILED("/details"),
}