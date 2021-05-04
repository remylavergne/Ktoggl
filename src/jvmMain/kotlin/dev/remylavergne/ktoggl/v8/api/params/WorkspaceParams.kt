package dev.remylavergne.ktoggl.v8.api.params

import dev.remylavergne.ktoggl.duplicate
import dev.remylavergne.ktoggl.report.common.Param
import dev.remylavergne.ktoggl.report.common.ParamBoolean


class WorkspaceParams {

    private val accumulator = mutableListOf<Param<*>>()

    fun get(): Array<Param<*>> {
        val params = accumulator.toTypedArray()
        if (params.duplicate()) {
            throw Exception("Error: Some request parameters are duplicated")
        }

        return accumulator.toTypedArray()
    }


    fun active(data: ParamActive.State = ParamActive.State.TRUE): Param<ParamActive.State> =
        ParamActive(key = "active", value = data)

    fun onlyTemplates(data: Boolean = false): Param<Boolean> =
        ParamBoolean(key = "only_templates", value = data)

    fun actualHours(data: Boolean = false): Param<Boolean> =
        ParamBoolean(key = "actual_hours", value = data)
}


data class ParamActive(override val key: String, override val value: State) : Param<ParamActive.State> {

    override fun get(): String = "$key=${value.v}"

    override fun valid(): Boolean = true

    enum class State(val v: String) {
        TRUE("true"),
        FALSE("false"),
        BOTH("BOTH"),
    }
} // TODO: Make generic