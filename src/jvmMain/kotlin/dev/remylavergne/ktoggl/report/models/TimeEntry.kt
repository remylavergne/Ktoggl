package dev.remylavergne.ktoggl.report.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TimeEntry(
    // id: time entry id
    val id: Long,
    // pid: project id
    val pid: Long,
    // project: project name for which the time entry was recorded
    val project: String,
    // project_color: Color used for this project
    @SerialName("project_color")
    val projectColor: String,
    // project_hex_color: Color HEX used for this project
    @SerialName("project_hex_color")
    val projectHexColor: String,
    // client: client name for which the time entry was recorded
    val client: String?,
    // tid: task id
    val tid: Long?,
    // task: task name for which the time entry was recorded
    val task: String?,
    // uid: user id whose time entry it is
    val uid: Long,
    // user: full name of the user whose time entry it is
    val user: String,
    // description: time entry description
    val description: String,
    // start: start time of the time entry in ISO 8601 date and time format (YYYY-MM-DDTHH:MM:SS)
    val start: String, // TODO: Translate to LocalDate / Date
    // end: end time of the time entry in ISO 8601 date and time format (YYYY-MM-DDTHH:MM:SS)
    val end: String, // TODO: Translate to LocalDate / Date
    // dur: time entry duration in milliseconds
    val dur: Long,
    // updated: last time the time entry was updated in ISO 8601 date and time format (YYYY-MM-DDTHH:MM:SS)
    val updated: String, // TODO: Translate to LocalDate / Date
    // use_stop: if the stop time is saved on the time entry, depends on user's personal settings.
    @SerialName("use_stop")
    val useStop: Boolean,
    // is_billable: boolean, if the time entry was billable or not
    @SerialName("is_billable")
    val isBillable: Boolean,
    // billable: billed amount
    val billable: Double?,
    // cur: billable amount currency
    val cur: String?,
    // tags: array of tag names, which assigned for the time entry
    val tags: List<String>,
)
