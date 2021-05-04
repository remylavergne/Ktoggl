package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.v8.api.params.ParamActive
import dev.remylavergne.ktoggl.v8.models.Client
import dev.remylavergne.ktoggl.v8.models.Project
import dev.remylavergne.ktoggl.v8.models.Response
import dev.remylavergne.ktoggl.report.service.KtogglClient as HttpClient


data class ClientsApi(
    private val httpClient: HttpClient,
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/clients"

    suspend fun createClient(client: dev.remylavergne.ktoggl.v8.models.Client): ApiResult<Response<Client>> {
        return httpClient.post(baseUrl, client)
    }

    suspend fun getClient(id: String): ApiResult<Response<Client>> {
        return httpClient.get("$baseUrl/$id")
    }

    suspend fun updateClient(client: Client): ApiResult<Response<Client>> {
        return httpClient.put("$baseUrl/${client.id}", client)
    }

    suspend fun deleteClient(id: String): ApiResult<Boolean> {
        return httpClient.delete<Boolean>("$baseUrl/$id")
    }

    suspend fun getClients(): ApiResult<List<Client>> {
        return httpClient.get(baseUrl)
    }

    suspend fun getClientProjects(
        projectId: String,
        active: ParamActive = ParamActive(key = "active", value = ParamActive.State.TRUE) // TODO: Change ?
    ): ApiResult<List<Project>> {
        return httpClient.getWithParams(active, url = "$baseUrl/$projectId/projects")
    }
}
