package dev.remylavergne.ktoggl

import dev.remylavergne.ktoggl.report.KtogglReportApi
import dev.remylavergne.ktoggl.report.business.KtogglReport
import dev.remylavergne.ktoggl.report.business.WeeklyProjectsTimeResult
import dev.remylavergne.ktoggl.report.models.BaseDetails
import dev.remylavergne.ktoggl.report.service.ApiResult
import kotlinx.coroutines.delay
import java.time.LocalDate


suspend fun main(args: Array<String>) {

    val reportApi: KtogglReport = KtogglReportApi {
        account {
            apiToken(API_KEY)
        }
    }

    val weeklyProjectsTime: ApiResult<WeeklyProjectsTimeResult> = reportApi.weeklyProjectsTime {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)

        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }

    when (weeklyProjectsTime) {
        is ApiResult.Success -> weeklyProjectsTime.data
        is ApiResult.Error -> TODO()
    }

    delay(2000)

    val details: ApiResult<BaseDetails> = reportApi.details(page = 1) {

        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }

    println(details)

    val allDetails: ApiResult<BaseDetails> = reportApi.detailsWithoutPaging {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-02-06"))
        until(LocalDate.parse("2021-04-17"))
    }

    println(allDetails)


    /*val summary = reportApi.summaryProjectsTasks {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }

     */

    /*val summary = reportApi.summaryProjectsUsers {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }

     */


    println()
}
