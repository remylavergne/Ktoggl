package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable


@Serializable
data class Response<T>(
    val data: T,
)
