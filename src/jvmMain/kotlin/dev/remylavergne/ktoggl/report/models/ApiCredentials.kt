package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.AccountBuilderDsl
import dev.remylavergne.ktoggl.report.ApiDsl
import dev.remylavergne.ktoggl.report.service.KtogglClient
import io.ktor.client.*

@ApiDsl
data class ApiCredentials(
    val account: Account,
    val ktogglClient: KtogglClient,
) {

    val httpClient: HttpClient = ktogglClient.clientInstance // TODO: Remove that -> instance private

    open class Builder {
        private lateinit var account: Account

        fun account(block: AccountBuilderDsl.() -> Unit) {
            val builder = AccountBuilderDsl()
            builder.apply(block)
            this.account = builder.build()
        }

        private fun generateClient(): KtogglClient {
            return KtogglClient(account)
        }

        fun build(): ApiCredentials = ApiCredentials(this.account, generateClient())

    }
}
