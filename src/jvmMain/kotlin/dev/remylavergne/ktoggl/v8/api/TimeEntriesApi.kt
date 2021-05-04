package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.KtogglClient

data class TimeEntriesApi(
    val httpKtogglClient: KtogglClient,
) {

    // TODO: https://github.com/toggl/toggl_api_docs/blob/master/chapters/time_entries.md
}
