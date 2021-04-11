package dev.remylavergne.ktoggl.report.service

import dev.remylavergne.ktoggl.report.models.Account
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

data class Client(val account: Account) {
    val instance: HttpClient = HttpClient(CIO) {
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
}
