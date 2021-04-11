package dev.remylavergne.ktoggl

import dev.remylavergne.ktoggl.report.common.Param


fun Array<out Param<*>>.duplicate(): Boolean {
    val toSet = this.map { it.value }.toSet()
    return toSet.size != this.size
}

fun Array<Param<*>>.toQueryParams(): String {
    return "?${this.joinToString(separator = "&") { it.get() }}"
}
