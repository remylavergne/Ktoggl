package dev.remylavergne.ktoggl.report.models


enum class Endpoint(val v: String) {
    WEEKLY("/weekly"),
    DETAILED("/details"),
    SUMMARY("/summary"),
}