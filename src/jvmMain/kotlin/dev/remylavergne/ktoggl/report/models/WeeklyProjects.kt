package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.common.CurrencyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeeklyProjectsTime(
    val title: Project,
    val pid: Long?,
    val totals: List<Long?>,
    val details: List<ProjectDetailsTime>
)

@Serializable
data class WeeklyProjectsEarnings(
    val title: Project,
    val pid: Long,
    val totals: List<CurrencyValue>,
    val details: List<ProjectDetailsEarning>
)

@Serializable
data class Project(
    val project: String?,
    val client: String?,
    val color: String?,
    @SerialName("hex_color")
    val hexColor: String?,
)

@Serializable
data class ProjectDetailsTime(
    val uid: Long,
    val title: User,
    val totals: List<Long?>,
)

@Serializable
data class ProjectDetailsEarning(
    val uid: Long,
    val title: User,
    val totals: List<CurrencyValue?>,
)

@Serializable
data class User(
    val user: String,
)
