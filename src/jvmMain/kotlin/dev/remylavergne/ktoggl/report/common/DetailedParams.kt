package dev.remylavergne.ktoggl.report.common

/**
 * In addition to the standard request parameters, there is one additional parameter in detailed reports.
 * As the returned data is paginated you have to add the page parameter.
 */
object DetailedParams {
    // integer, default 1
    fun page(data: Int = 1): Param<Int> =
        ParamInt(key = "page", value = data)
}