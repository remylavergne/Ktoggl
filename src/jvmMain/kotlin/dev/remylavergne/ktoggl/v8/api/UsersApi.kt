package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.KtogglClient


// TODO: https://github.com/toggl/toggl_api_docs/blob/master/chapters/users.md
data class UsersApi(
    val httpKtogglClient: KtogglClient,
)
