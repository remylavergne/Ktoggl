package dev.remylavergne.ktoggl.v8

import dev.remylavergne.ktoggl.report.ApiCredentialsBuilderDsl
import dev.remylavergne.ktoggl.report.models.ApiCredentials

inline fun KtogglV8Api(crossinline block: ApiCredentialsBuilderDsl.() -> Unit): KtogglV8 {
    val apiCredentialsBuilder: ApiCredentials = ApiCredentialsBuilderDsl().apply(block).build()
    return KtogglV8(apiCredentialsBuilder)
}