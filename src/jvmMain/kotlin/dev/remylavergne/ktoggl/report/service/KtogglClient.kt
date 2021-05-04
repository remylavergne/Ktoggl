package dev.remylavergne.ktoggl.report.service

import dev.remylavergne.ktoggl.report.common.Param
import dev.remylavergne.ktoggl.report.models.Account
import dev.remylavergne.ktoggl.toQueryParams
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay

// TODO: Rendre le client commun au projet
data class KtogglClient(val account: Account) {
    val clientInstance: HttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }

        install(Auth) {
            basic {
                sendWithoutRequest = true
                username = account.apiToken
                password = "api_token"
            }
        }
    }

    suspend inline fun <reified T> getWithParams(
        vararg params: Param<*> = arrayOf(),
        url: String,
    ): ApiResult<T> {
        delay(1100) // API limit one call per seconds
        val queryParams: String = arrayOf(*params).toQueryParams()

        val result: T =
            clientInstance.get("$url$queryParams")

        return ApiResult.Success(result)
    }

    suspend inline fun <reified T> get(endpoint: String): ApiResult<T> {
        delay(1100) // API limit one call per seconds

        val result: T = clientInstance.get(endpoint)

        return ApiResult.Success(result)
    }


    suspend inline fun <reified T, U> put(endpoint: String, data: U): ApiResult<T> {
        delay(1100) // API limit one call per seconds

        val result: T = clientInstance.put(endpoint) {
            this.body = data as Any
        }

        return ApiResult.Success(result)
    }

    suspend inline fun <reified T, U> post(endpoint: String, data: U): ApiResult<T> {
        delay(1100) // API limit one call per seconds

        val result: T = clientInstance.put(endpoint) {
            this.body = data as Any
        }

        return ApiResult.Success(result)
    }

    suspend inline fun <reified T> delete(endpoint: String): ApiResult<T> {
        delay(1100) // API limit one call per seconds

        val result: T = clientInstance.delete(endpoint)

        return ApiResult.Success(result)
    }

}
