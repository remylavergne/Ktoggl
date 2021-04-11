package dev.remylavergne.ktoggl

import dev.remylavergne.ktoggl.report.*
import dev.remylavergne.ktoggl.report.business.WeeklyProjectsTimeResult
import dev.remylavergne.ktoggl.report.common.Params
import dev.remylavergne.ktoggl.report.service.ApiResult
import io.ktor.client.*
import java.time.LocalDate


suspend fun main(args: Array<String>) {

    val reportApi: KtogglReportApi = KtogglReportApi {
        account {
            apiToken(API_KEY)
        }
    }

    val weeklyProjectsTime: ApiResult<WeeklyProjectsTimeResult> = reportApi.weeklyProjectsTime {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-02-22"))
    }
}
