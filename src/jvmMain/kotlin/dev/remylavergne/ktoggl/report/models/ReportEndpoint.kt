package dev.remylavergne.ktoggl.report.models

interface Endpoint

enum class ReportEndpoint(val v: String): Endpoint {
    WEEKLY("/weekly"),
    DETAILED("/details"),
    SUMMARY("/summary"),
}

enum class V8Endpoint(val v: String): Endpoint {
    PROJECTS("/projects"),
}