package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Users(
    val id: Int,
    val default_wid: Int,
    val email: String,
    val fullname: String,
    val jquery_timeofday_format: String,
    val jquery_date_format: String,
    val timeofday_format: String,
    val date_format: String,
    val store_start_and_stop_time: Boolean,
    val beginning_of_week: Int,
    val language: String,
    val image_url: String,
    val sidebar_piechart: Boolean,
    val at: String,
    val created_at: String,
    val retention: Int,
    val record_timeline: Boolean,
    val render_timeline: Boolean,
    val timeline_enabled: Boolean,
    val timeline_experiment: Boolean,
    val should_upgrade: Boolean,
    val timezone: String,
    val openid_enabled: Boolean,
    val send_product_emails: Boolean,
    val send_weekly_report: Boolean,
    val send_timer_notifications: Boolean,
    val invitation: Invitation,
    val duration_format: String,
)

@Serializable
class Invitation {}

@Serializable
data class UserTime(
    @SerialName("user_id")
    val userId: Int,
    val duration: Int,
)

@Serializable
data class UserActivity(
    @SerialName("user_id")
    val userId: Long,
    @SerialName("project_id")
    val projectId: Long,
    val duration: Long,
    val description: String,
)

