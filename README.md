# Ktoggl

A library, fully write in Kotlin ❤️, to wrap *Toggl API*.

This library evolves during [Ktoggl-CLI](https://github.com/remylavergne/Ktoggl-CLI) development. It's why some features aren't available yet.

### Report API

First, get a **Ktoggl Report** consumer:

```kotlin
 val reportApi: KtogglReportApi = KtogglReportApi {
        account {
            apiToken(API_KEY)
        }
    }
```

Then, consume the API:

- Weekly

```kotlin
// Weekly example
val weeklyProjectsTime: ApiResult<WeeklyProjectsTimeResult> = reportApi.weeklyProjectsTime {
    userAgent(USER_AGENT)
    workspaceId(WORKSPACE_ID)
    since(LocalDate.parse("2021-02-22"))
}
```

- Detailed -> Wrapper allowed to retrieve all data at once if a paging exists (more than 50 tasks available)

```kotlin
// Details with specific page
 val detailed: ApiResult<BaseDetailed> = reportApi.details(page = 2) {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }
```

```kotlin
// All details -> pagination is automatically handled
 val detailed: ApiResult<BaseDetailed> = ktogglReportApi.detailsWithoutPaging {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }
```

- Summary
- Project dashboard


Toggl API v8
====================

* [Authenticate and get user data]
    - HTTP Basic Auth with e-mail and password
    - HTTP Basic Auth with API token
    - Authentication with a session cookie
    - Destroy the session
* [Clients]
    - create a client
    - get client details
    - update a client
    - delete a client
    - get clients visible to user
    - get client projects
* [Groups]
    - create a group
    - update a group
    - delete a group
* [Projects]
    - create a project
    - get project data
    - update project data
    - delete a project
    - get project users
    - get project tasks
    - delete multiple projects
* [Project users]
    - create a project user
    - update a project user
    - delete a project user
    - add multiple users to a project
    - update multiple project users
    - delete multiple project users
* [Tags]
    - create a tag
    - update a tag
    - delete a tag
* [Tasks] *(available only for pro workspaces)*
    - create a task
    - get task details
    - update a task
    - delete a task
    - update multiple tasks
    - delete multiple tasks
* [Time entries]
    - create a time entry
    - start a time entry
    - stop a time entry
    - get time entry details
    - update time entry
    - delete time entry
    - get time entries started in a specific time range
    - bulk update time entries tags
* [Users]
    - get current user data and time entries
    - update current user data
    - reset API token
    - sign up new user
* [Workspaces]
    - get user workspaces
    - get workspace users
    - get workspace clients
    - get workspace groups
    - get workspace projects
    - get workspace tasks
    - get workspace tags 👈
* [Workspace users]
    - invite users to workspace
    - update workspace user
    - delete workspace user
    - get workspace users for a workspace
* [Dashboard]
    - Get a generic overview of your team

