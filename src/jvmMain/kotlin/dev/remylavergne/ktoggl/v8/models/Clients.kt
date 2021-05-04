package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable


@Serializable
data class Client(
    val id: Int,
    val wid: Int,
    val name: String,
    val at: String,
    val notes: String,
    val hrate: Int,
    val cur: String,
)