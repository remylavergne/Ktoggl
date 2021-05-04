package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.report.service.KtogglClient
import dev.remylavergne.ktoggl.v8.models.WorkspaceUser

data class WorkspaceUsersApi(
    private val ktogglClient: KtogglClient,
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/workspace_users/"

    suspend fun deleteUser(userId: String): ApiResult<Boolean> {
        return ktogglClient.delete("$baseUrl/$userId")
    }

    suspend fun getUsers(workspaceId: String): ApiResult<List<WorkspaceUser>> {
        return ktogglClient.get("$baseUrl/$workspaceId")
    }



}
