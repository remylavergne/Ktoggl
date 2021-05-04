package dev.remylavergne.ktoggl.v8.api

import dev.remylavergne.ktoggl.report.service.ApiResult
import dev.remylavergne.ktoggl.report.service.KtogglClient
import dev.remylavergne.ktoggl.v8.models.DashboardData

/**
 * Dashboard's main purpose is to give an overview of what users in the workspace are doing and have been doing.
 * Dashboard request returns two objects:
 *
 * Activity
 * Most active user
 *
 * The activity object holds the data of 20 latest actions in the workspace. Activity object has the following properties
 *
 * user_id: user ID
 * project_id: project ID (ID is 0 if time entry doesn't have project connected to it)
 * duration: time entry duration in seconds. If the time entry is currently running, the duration attribute contains
 * a negative value, denoting the start of the time entry in seconds since epoch (Jan 1 1970). The correct duration can
 * be calculated as current_time + duration, where current_time is the current time in seconds since epoch.
 * description: (Description property is not present if time entry description is empty)
 * stop: time entry stop time (ISO 8601 date and time. Stop property is not present when time entry is still running)
 * tid: task id, if applicable
 *
 * The most active user object holds the data of the top 5 users who have tracked the most time during last 7 days.
 * Most active user object has the following properties
 *
 * user_id: user ID
 * duration: Sum of time entry durations that have been created during last 7 days
 *
 */
data class DashboardApi(
    private val httpKtogglClient: KtogglClient,
) {

    private val baseUrl = "https://api.track.toggl.com/api/v8/dashboard"

    suspend fun getDashboardData(workspaceId: String): ApiResult<DashboardData> {
        return httpKtogglClient.get("$baseUrl/$workspaceId")
    }
}
