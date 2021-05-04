package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable


@Serializable
data class Task(
    val name: String,
    val id: Int,
    val wid: Int,
    val pid: Int,
    val uid: Int,
    val active: Boolean,
    val at: String,
    val estimated_seconds: Int,
) {
}