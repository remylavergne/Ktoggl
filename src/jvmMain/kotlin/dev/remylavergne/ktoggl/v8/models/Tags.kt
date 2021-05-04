package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable

@Serializable
data class Tags(
    val id: Int,
    val wid: Int,
    val name: String,
    val at: String,
)