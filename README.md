# Ktoggl

⚠️ Only weekly is available. API is under heavy development 😅

## How to use it

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

V8:

🛑 Everything (in progress... and future integration with [Ktoggl CLI](https://github.com/remylavergne/Ktoggl-CLI))
