package dev.remylavergne.ktoggl.report.common

interface Param<T> {
    val key: String
    val value: T
    fun get(): String
    fun valid(): Boolean
}
