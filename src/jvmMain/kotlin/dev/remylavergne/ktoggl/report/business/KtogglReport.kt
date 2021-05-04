package dev.remylavergne.ktoggl.report.business

import dev.remylavergne.ktoggl.REPORT_BASE_URL
import dev.remylavergne.ktoggl.report.common.*
import dev.remylavergne.ktoggl.report.common.SummaryParams.grouping
import dev.remylavergne.ktoggl.report.common.SummaryParams.subgrouping
import dev.remylavergne.ktoggl.report.models.*
import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.toQueryParams
import io.ktor.client.request.*
import kotlinx.coroutines.delay


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
            reportEndpoint = ReportEndpoint.WEEKLY,
        )
    }

    suspend fun weeklyProjectsEarnings(block: Params.() -> Unit): ApiResult<WeeklyProjectsEarningsResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS),
            reportEndpoint = ReportEndpoint.WEEKLY,
        )
    }

    suspend fun weeklyUsersTime(block: Params.() -> Unit): ApiResult<WeeklyUsersTimeResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate(),
            reportEndpoint = ReportEndpoint.WEEKLY,
        )
    }

    suspend fun weeklyUsersEarnings(block: Params.() -> Unit): ApiResult<WeeklyUsersEarningsResult> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            WeeklyParams.grouping(ParamGrouping.Grouping.USERS),
            WeeklyParams.calculate(ParamCalculate.Calculate.EARNINGS),
            reportEndpoint = ReportEndpoint.WEEKLY,
        )
    }

    /**
     * By default, it only returns last week
     */
    suspend fun details(page: Int = 1, block: Params.() -> Unit): ApiResult<BaseDetails> {
        if (page <= 0) {
            throw Exception("Minimum page must be 1")
        }
        val params = Params().apply(block).get()
        val pageParam: Param<Int> = DetailedParams.page(page)

        return makeApiCall(*params, pageParam, reportEndpoint = ReportEndpoint.DETAILED)
    }

    suspend fun detailsWithoutPaging(block: Params.() -> Unit): ApiResult<BaseDetails> {
        // Checks
        val params = Params().apply(block).get()
        val firstResponse =
            makeApiCall<BaseDetails>(*params, DetailedParams.page(1), reportEndpoint = ReportEndpoint.DETAILED)

        if (firstResponse is ApiResult.Success && firstResponse.data.totalCount > firstResponse.data.perPage) {
            // More than one page -> retrieve every data
            val manyResponses = mutableListOf<ApiResult<BaseDetails>>()
            manyResponses.add(firstResponse)

            // Make others requests
            var remainingPages: Int =
                (firstResponse.data.totalCount - firstResponse.data.perPage) / firstResponse.data.perPage

            if (firstResponse.data.totalCount > firstResponse.data.perPage && firstResponse.data.totalCount % firstResponse.data.perPage > 0) {
                remainingPages++
            }

            for (page in 1..remainingPages) {
                val remainingCall =
                    makeApiCall<BaseDetails>(
                        *params,
                        DetailedParams.page(page + 1),
                        reportEndpoint = ReportEndpoint.DETAILED
                    )

                manyResponses.add(remainingCall)
            }

            val allData = manyResponses
                .filterIsInstance<ApiResult.Success<BaseDetails>>()
                .map { response -> response.data.data }

            val updatedResponse = BaseDetails(
                totalGrand = firstResponse.data.totalGrand,
                totalBillable = firstResponse.data.totalBillable,
                totalCurrencies = firstResponse.data.totalCurrencies,
                totalCount = firstResponse.data.totalCount,
                perPage = firstResponse.data.perPage,
                data = allData.flatten(),
            )

            return ApiResult.Success(updatedResponse)
        } else {
            return firstResponse
        }
    }

    /**
     * Summary Projects
     */

    suspend fun summaryProjectsTimeEntries(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTimeEntry>> { // TODO: Alias ?
        val params = Params().apply(block).get()
        return makeApiCall(
            *params,
            grouping(),
            subgrouping(),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryProjectsTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return makeApiCall(
            *params,
            grouping(),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryProjectsUsers(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryUser>> {
        val params = Params().apply(block).get()
        return makeApiCall(
            *params,
            grouping(),
            subgrouping(ParamSummarySubgrouping.By.USERS),
            reportEndpoint = ReportEndpoint.SUMMARY,
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
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsProjects(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryProject>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.PROJECTS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryClientsUsers(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryUser>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.CLIENTS),
            subgrouping(ParamSummarySubgrouping.By.USERS),
            reportEndpoint = ReportEndpoint.SUMMARY,
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
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersTasks(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryTask>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.TASKS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersProjects(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryProject>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.PROJECTS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    suspend fun summaryUsersClients(block: Params.() -> Unit): ApiResult<BaseSummaryProjects<SummaryProject, SummaryClient>> {
        val params = Params().apply(block).get()
        return this.makeApiCall(
            *params,
            grouping(ParamSummaryGrouping.By.USERS),
            subgrouping(ParamSummarySubgrouping.By.CLIENTS),
            reportEndpoint = ReportEndpoint.SUMMARY,
        )
    }

    /**
     * Private
     */

    private suspend inline fun <reified T> makeApiCall(
        vararg params: Param<*> = arrayOf(),
        reportEndpoint: ReportEndpoint
    ): ApiResult<T> {
        delay(1100) // API limit one call per seconds
        val queryParams: String = arrayOf(*params).toQueryParams()

        val result: T =
            account.httpClient.get("$REPORT_BASE_URL${reportEndpoint.v}$queryParams")

        return ApiResult.Success(result)
    }
}