package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.service.Client
import dev.remylavergne.ktoggl.report.AccountBuilderDsl
import dev.remylavergne.ktoggl.report.ApiDsl
import io.ktor.client.*

@ApiDsl
data class ApiCredentials(
    val account: Account,
    private val client: Client,
) {

    val httpClient: HttpClient = client.instance

    open class Builder {
        private lateinit var account: Account

        fun account(block: AccountBuilderDsl.() -> Unit) {
            val builder = AccountBuilderDsl()
            builder.apply(block)
            this.account = builder.build()
        }

        private fun generateClient(): Client {
            return Client(account)
        }

        fun build(): ApiCredentials = ApiCredentials(this.account, generateClient())

    }
}
