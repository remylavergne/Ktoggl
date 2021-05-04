package dev.remylavergne.ktoggl.v8.models

import kotlinx.serialization.Serializable

@Serializable
data class Workspace(
    val id: Int,
    val name: String,
    val premium: Boolean,
    val admin: Boolean,
    val default_hourly_rate: Int,
    val default_currency: String,
    val only_admins_may_create_projects: Boolean,
    val only_admins_see_billable_rates: Boolean,
    val rounding: Int,
    val rounding_minutes: Int,
    val at: String, // TODO: To date
    val logo_url: String,
)
