package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable


@Serializable
data class Invite(
    val emails: Array<out String>,
)

@Serializable
data class InviteResponseWrapper(
    val data: List<InviteResponse>,
    val notifications: List<String>,
)

@Serializable
data class InviteResponse(
    val id: Int,
    val uid: Int,
    val wid: Int,
    val admin: Boolean,
    val active: Boolean,
    val email: String,
    val invite_url: String,
)