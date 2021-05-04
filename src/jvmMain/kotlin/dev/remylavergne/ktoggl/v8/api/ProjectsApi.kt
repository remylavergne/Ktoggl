package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.KtogglClient

// TODO: https://github.com/toggl/toggl_api_docs/blob/master/chapters/projects.md

data class ProjectsApi(
    val httpKtogglClient: KtogglClient // TODO: Rename Ktoggl Client name
) {

}


