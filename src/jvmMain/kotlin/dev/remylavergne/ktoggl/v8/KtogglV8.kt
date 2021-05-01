package dev.remylavergne.ktoggl.v8

import dev.remylavergne.ktoggl.report.models.ApiCredentials
import dev.remylavergne.ktoggl.report.service.ApiResult
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

data class KtogglV8(
    private val account: ApiCredentials,
) {

    fun workspace(id: String): Workspace {
        return Workspace(id, account.httpClient)
    }
    
    companion object {

        suspend inline fun <reified T> makeApiCall(httpClient: HttpClient, endpoint: String): ApiResult<T> {
            delay(1100) // API limit one call per seconds

            val result: T = httpClient.get(endpoint)

            return ApiResult.Success(result)
        }
    }
}

data class Workspace(
    private val id: String,
    private val client: HttpClient
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/workspaces/$id"

    suspend fun getTags(): ApiResult<List<Tags>> {
        return KtogglV8.makeApiCall(client, "$baseUrl/tags")
    }
}

@Serializable
data class Tags(
    private val id: Int,
    private val wid: Int,
    private val name: String,
    private val at: String,
)