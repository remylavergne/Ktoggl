package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.ApiDsl

@ApiDsl
data class Account(
    val apiToken: String,
) {

    open class Builder(
        private var apiToken: String? = null,
    ) {

        fun apiToken(apiToken: String) {
            this.apiToken = apiToken
        }

        fun build(): Account {
            if (apiToken.isNullOrEmpty()) {
                throw Exception("Account missing parameters")
            }

            return Account(apiToken = this.apiToken!!)
        }
    }
}
