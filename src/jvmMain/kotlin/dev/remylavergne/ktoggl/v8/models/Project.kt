package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable


@Serializable
data class Project(
    val id: Int,
    val wid: Int,
    val cid: Int,
    val name: String,
    val billable: Boolean,
    val is_private: Boolean,
    val active: Boolean,
    val at: String,
)
