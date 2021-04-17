package dev.remylavergne.ktoggl.report.business

import dev.remylavergne.ktoggl.REPORT_BASE_URL
import dev.remylavergne.ktoggl.duplicate
import dev.remylavergne.ktoggl.report.common.*
import dev.remylavergne.ktoggl.report.common.SummaryParams.grouping
import dev.remylavergne.ktoggl.report.common.SummaryParams.subgrouping
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

    /**
     * By default, it only returns last week
     */
    suspend fun details(page: Int = 1, block: Params.() -> Unit): ApiResult<BaseDetailed> {
        if (page <= 0) {
            throw Exception("Minimum page must be 1")
        }
        val params = Params().apply(block).get()
        val pageParam: Param<Int> = DetailedParams.page(page)
        return this.makeApiCall(*params, pageParam, endpoint = Endpoint.DETAILED)
    }

    /**
     * Summary Projects
     */

    suspend fun summaryProjectsTimeEntries(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTimeEntry>> { // TODO: Alias ?
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(),
            subgrouping(),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryProjectsTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryProjectsUsers(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryUser>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(),
            subgrouping(ParamSummarySubgrouping.By.USERS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    /**
     * Summary Clients
     */

    suspend fun summaryClientsTimeEntries(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTimeEntry>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.TIME_ENTRIES),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsProjects(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryProject>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.PROJECTS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsUsers(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryUser>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.USERS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    /**
     * Summary Users
     */

    suspend fun summaryUsersTimeEntries(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTimeEntry>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.TIME_ENTRIES),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersProjects(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryProject>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.PROJECTS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersClients(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryClient>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.CLIENTS),
            endpoint = Endpoint.SUMMARY,
        )
    }

    /**
     * Private
     */

    private suspend inline fun <reified T> makeApiCall(
        vararg params: Param<*> = arrayOf(),
        endpoint: Endpoint
    ): ApiResult<T> {
        
        val queryParams: String = arrayOf(*params).toQueryParams()

        val result: T =
            account.httpClient.use { client: HttpClient -> client.get<T>("$REPORT_BASE_URL${endpoint.v}$queryParams") }

        return ApiResult.Success(result)
    }
}

data class Request<T>(
    val endpoint: Endpoint,
    val params: Array<Param<*>> = emptyArray(),
) {

}