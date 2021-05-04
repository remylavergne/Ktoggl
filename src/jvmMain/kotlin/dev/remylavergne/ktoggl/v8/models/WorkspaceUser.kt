package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WorkspaceUser(
    val id: Int,
    val uid: Int,
    val wid: Int,
    val admin: Boolean,
    val active: Boolean,
    val email: String,
    val at: String,
    val name: String,
    val invite_url: String,
)

@Serializable
data class WorkspaceUserUpdate(
    @SerialName("workspace_user")
    val workspaceUser: WorkspaceUser,
)


