package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.report.service.KtogglClient
import dev.remylavergne.ktoggl.v8.models.Group
import dev.remylavergne.ktoggl.v8.models.Response


data class GroupsApi(
    private val httpKtogglClient: KtogglClient,
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/groups"

    suspend fun createGroup(group: Group): ApiResult<Response<Group>> {
        return httpKtogglClient.post(baseUrl, group)
    }

    suspend fun updateGroup(group: Group): ApiResult<Response<Group>> {
        return httpKtogglClient.put("$baseUrl/${group.id}", group)
    }

    suspend fun deleteGroup(id: String): ApiResult<Response<Group>> {
        return httpKtogglClient.delete("$baseUrl/$id")
    }
}
