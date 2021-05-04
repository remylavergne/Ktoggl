package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DashboardData(
    @SerialName("most_active_user")
    val mostActiveUser: List<UserTime>,
    val activity: List<UserActivity>,
)
