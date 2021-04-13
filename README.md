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

```kotlin
// Weekly example
val weeklyProjectsTime: ApiResult<WeeklyProjectsTimeResult> = reportApi.weeklyProjectsTime {
    userAgent(USER_AGENT)
    workspaceId(WORKSPACE_ID)
    since(LocalDate.parse("2021-02-22"))
}
```

```kotlin
// Details
 val detailed: ApiResult<BaseDetailed> = reportApi.detailed(page = 2) {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-04-06"))
        until(LocalDate.parse("2021-04-07"))
    }
```

## TODO

Report:

✅ Weekly

✅ Detailed

🛑 Summary

🛑 Project dashboard

V8:

🛑 Everything