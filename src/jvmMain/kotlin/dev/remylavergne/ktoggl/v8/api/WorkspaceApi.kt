package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.report.service.KtogglClient
import dev.remylavergne.ktoggl.v8.api.params.ParamActive
import dev.remylavergne.ktoggl.v8.api.params.WorkspaceParams
import dev.remylavergne.ktoggl.v8.models.*
import dev.remylavergne.ktoggl.v8.models.Client as ClientModel


data class WorkspaceApi(
    private val ktogglClient: KtogglClient,
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/workspaces"

    suspend fun getWorkspaces(): ApiResult<List<Workspace>> {
        return ktogglClient.get(baseUrl)
    }

    suspend fun getWorkspace(id: String): ApiResult<WorkspaceWrapper> {
        return ktogglClient.get("$baseUrl/$id")
    }

    suspend fun getTags(workspaceId: String): ApiResult<List<Tags>> {
        return ktogglClient.get("$baseUrl/$workspaceId/tags")
    }

    // TODO: To test
    suspend fun updateWorkspace(workspaceId: String, data: Workspace): ApiResult<WorkspaceWrapper> {
        return ktogglClient.put("$baseUrl/$workspaceId", data)
    }

    // To get a successful response, the token owner must be workspace admin.
    suspend fun getUsers(workspaceId: String): ApiResult<List<Users>> {
        return ktogglClient.get("$baseUrl/$workspaceId/users")
    }

    // To get a successful response, the token owner must be workspace admin.
    suspend fun getClients(workspaceId: String): ApiResult<List<ClientModel>> {
        return ktogglClient.get("$baseUrl/$workspaceId/clients")
    }

    // To get a successful response, the token owner must be workspace admin.
    suspend fun getGroups(workspaceId: String): ApiResult<List<Group>> {
        return ktogglClient.get("$baseUrl/$workspaceId/groups")
    }

    suspend fun getProjects(workspaceId: String, block: WorkspaceParams.() -> Unit): ApiResult<List<Project>> {
        val params = WorkspaceParams().apply(block).get()

        return ktogglClient.getWithParams(*params, url = "$baseUrl/$workspaceId/projects")
    }

    suspend fun getTasks(
        workspaceId: String,
        active: ParamActive = ParamActive(key = "active", value = ParamActive.State.TRUE)
    ): ApiResult<List<Task>> {

        return ktogglClient.getWithParams(active, url = "$baseUrl/$workspaceId/tasks")
    }

    suspend fun invite(workspaceId: String, vararg email: String): ApiResult<InviteResponseWrapper> {
        return ktogglClient.post("$baseUrl/$workspaceId/invite", Invite(emails = email))
    }
}
