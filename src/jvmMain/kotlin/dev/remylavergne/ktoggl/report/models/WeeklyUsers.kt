package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.common.CurrencyValue
import kotlinx.serialization.Serializable

@Serializable
data class WeeklyUsersTime(
    val title: User,
    val uid: Long,
    val totals: List<Long?>,
    val details: List<UserDetailTime>,
)

@Serializable
data class WeeklyUsersEarnings(
    val title: User,
    val uid: Long,
    val totals: List<CurrencyValue>,
    val details: List<UserDetailEarnings>,
)

@Serializable
data class UserDetailTime(
    val pid: Long,
    val title: Project,
    val totals: List<Long?>,
)

@Serializable
data class UserDetailEarnings(
    val pid: Long,
    val title: Project,
    val totals: List<CurrencyValue>,
)
