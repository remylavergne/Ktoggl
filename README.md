# Ktoggl

⚠️ Only weekly is available. API is under heavy development 😅

# How to use it

First, get a **Ktoggl API** consumer:

```kotlin
 val reportApi: KtogglReportApi = KtogglReportApi {
        account {
            apiToken(API_KEY)
        }
    }
```

Then, consume the API: 

```kotlin
val weeklyProjectsTime: ApiResult<WeeklyProjectsTimeResult> = reportApi.weeklyProjectsTime {
        userAgent(USER_AGENT)
        workspaceId(WORKSPACE_ID)
        since(LocalDate.parse("2021-02-22"))
    }
```

