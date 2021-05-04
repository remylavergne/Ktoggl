package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.KtogglClient

data class ProjectUsersApi(
    private val httpKtogglClient: KtogglClient,
) {

}

// TODO: https://github.com/toggl/toggl_api_docs/blob/master/chapters/project_users.md