package dev.remylavergne.ktoggl.report

import dev.remylavergne.ktoggl.report.business.KtogglReport
import dev.remylavergne.ktoggl.report.models.ApiCredentials

inline fun KtogglReportApi(crossinline block: ApiCredentialsBuilderDsl.() -> Unit): KtogglReport {
    val apiCredentialsBuilder: ApiCredentials = ApiCredentialsBuilderDsl().apply(block).build()
    return KtogglReport(apiCredentialsBuilder)
}