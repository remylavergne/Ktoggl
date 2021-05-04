package dev.remylavergne.ktoggl.v8

import dev.remylavergne.ktoggl.v8.api.WorkspaceApi
import dev.remylavergne.ktoggl.report.models.ApiCredentials

data class KtogglV8(
    private val account: ApiCredentials,
) {

    fun workspace(): WorkspaceApi {
        return WorkspaceApi(account.ktogglClient)
    }
}
