package dev.remylavergne.ktoggl.report.common

import java.time.LocalDate


/**
 * API optionals query params
 */
class Params {

    private val accumulator = mutableListOf<Param<*>>()

    fun get() = accumulator.toTypedArray()

    // Params mandatory // TODO: Delete ?!
    fun userAgent(data: String): Param<String> {
        val paramString = ParamString(key = "user_agent", value = data)
        accumulator.add(paramString)
        return paramString
    }

    fun workspaceId(data: String): Param<String> {
        val paramString = ParamString(key = "workspace_id", value = data)
        accumulator.add(paramString)
        return paramString
    }

    // Optionals
    fun since(data: LocalDate): Param<LocalDate> {
        val paramDate = ParamDate(key = "since", value = data)
        accumulator.add(paramDate)
        return paramDate
    }

    // until: ISO 8601 date (YYYY-MM-DD) format. Note: Maximum date span (until - since) is one year. Defaults to today, unless since is in future or more than year ago, in this case until is since + 6 days.
    fun until(data: LocalDate): Param<LocalDate> {
        val paramDate = ParamDate(key = "until", value = data)
        accumulator.add(paramDate)
        return paramDate
    }

    // billable: "yes", "no", or "both". Defaults to "both".
    fun billable(data: ParamBillable.Value): Param<ParamBillable.Value> {
        val paramBillable = ParamBillable(key = "billable", value = data)
        accumulator.add(paramBillable)
        return paramBillable
    }

    // client_ids: A list of client IDs separated by a comma. Use "0" if you want to filter out time entries without a client.
    fun clientIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "client_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }

    // project_ids: A list of project IDs separated by a comma. Use "0" if you want to filter out time entries without a project.
    fun projectIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "project_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }

    // user_ids: A list of user IDs separated by a comma.
    fun userIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "user_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }

    // members_of_group_ids: A list of group IDs separated by a comma. This limits provided user_ids to the members of the given groups.
    fun membersOfGroupIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "members_of_group_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }


    // or_members_of_group_ids: A list of group IDs separated by a comma. This extends provided user_ids with the members of the given groups.
    fun orMembersOfGroupIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "or_members_of_group_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }


    // tag_ids: A list of tag IDs separated by a comma. Use "0" if you want to filter out time entries without a tag.
    fun tagsIds(data: List<String>): Param<List<String>> {
        val paramIdsDefault = ParamIdsDefault(key = "tag_ids", value = data)
        accumulator.add(paramIdsDefault)
        return paramIdsDefault
    }

    // task_ids: A list of task IDs separated by a comma. Use "0" if you want to filter out time entries without a task.
    fun taskIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "task_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }

    // time_entry_ids: A list of time entry IDs separated by a comma.
    fun timeEntryIds(data: List<String>): Param<List<String>> {
        val paramIds = ParamIds(key = "time_entry_ids", value = data)
        accumulator.add(paramIds)
        return paramIds
    }

    // description: Matches against time entry descriptions.
    fun description(data: String): Param<String> {
        val paramString = ParamString(key = "description", value = data)
        accumulator.add(paramString)
        return paramString
    }

    // without_description: "true" or "false". Filters out the time entries which do not have a description (literally "(no description)").
    fun withoutDescription(data: Boolean): Param<Boolean> {
        val paramBoolean = ParamBoolean(key = "without_description", value = data)
        accumulator.add(paramBoolean)
        return paramBoolean
    }

    // order_field:
    fun orderField(data: ParamOrderField.WeeklyValue): Param<ParamOrderField.WeeklyValue> {
        val paramOrderField = ParamOrderField(key = "order_field", value = data) // TODO: Adapt Enum value
        accumulator.add(paramOrderField)
        return paramOrderField
    }


    // order_desc: "on" for descending, or "off" for ascending order.
    fun orderDesc(data: ParamSwitch.Value): Param<ParamSwitch.Value> {
        val paramSwitch = ParamSwitch(key = "order_desc", value = data)
        accumulator.add(paramSwitch)
        return paramSwitch
    }


    // distinct_rates: "on" or "off". Defaults to "off".
    fun distinctRates(data: ParamSwitch.Value): Param<ParamSwitch.Value> {
        val paramSwitch = ParamSwitch(key = "distinct_rates", value = data)
        accumulator.add(paramSwitch)
        return paramSwitch
    }


    // rounding: "on" or "off". Defaults to "off". Rounds time according to workspace settings.
    fun rounding(data: ParamSwitch.Value): Param<ParamSwitch.Value> {
        val paramSwitch = ParamSwitch(key = "rounding", value = data)
        accumulator.add(paramSwitch)
        return paramSwitch
    }

    // display_hours: "decimal" or "minutes". Defaults to "minutes". Determines whether to display hours as a decimal number or with minutes.
    fun displayHours(data: ParamHours.Value = ParamHours.Value.MINUTES): Param<ParamHours.Value> {
        val paramHours = ParamHours(key = "display_hours", value = data)
        accumulator.add(paramHours)
        return paramHours
    }
}

data class ParamString(override val key: String, override val value: String) : Param<String> {
    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = value.isNotEmpty()
}

data class ParamBoolean(override val key: String, override val value: Boolean) : Param<Boolean> {
    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = true
}

// TODO: Check ISO 8601
data class ParamDate(override val key: String, override val value: LocalDate) : Param<LocalDate> {
    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = true // TODO: Validation
}

data class ParamBillable(override val key: String, override val value: Value = Value.BOTH) :
    Param<ParamBillable.Value> {
    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = true

    enum class Value(val v: String) {
        YES("yes"),
        NO("no"),
        BOTH("both"),
    }
}

data class ParamOrderField(
    override val key: String,
    override val value: WeeklyValue
) : // TODO Adapt with other order available
    Param<ParamOrderField.WeeklyValue> {
    override fun get(): String = "$key=${value.v}"

    override fun valid(): Boolean = true

    // "title", "day1", "day2", "day3", "day4", "day5", "day6", "day7", or "week_total"
    enum class WeeklyValue(val v: String) {
        TITLE("title"),
        DAY_1("day1"),
        DAY_2("day2"),
        DAY_3("day3"),
        DAY_4("day4"),
        DAY_5("day5"),
        DAY_6("day6"),
        DAY_7("day7"),
        WEEK_TOTAL("week_total"),
    }

    // TODO
    /*
    For detailed reports: "date", "description", "duration", or "user"
    For summary reports: "title", "duration", or "amount"
    For weekly reports: "title", "day1", "day2", "day3", "day4", "day5", "day6", "day7", or "week_total"
    */
}

data class ParamSwitch(override val key: String, override val value: Value) : Param<ParamSwitch.Value> {
    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = true

    enum class Value(val v: String) {
        ON("on"),
        OFF("off"),
    }
}

data class ParamHours(override val key: String, override val value: Value = Value.MINUTES) : Param<ParamHours.Value> {
    override fun get(): String = "$key=${value.v}"

    override fun valid(): Boolean = true

    enum class Value(val v: String) {
        DECIMAL("decimals"),
        MINUTES("minutes"),
    }
}

data class ParamGrouping(override val key: String, override val value: Grouping) : Param<ParamGrouping.Grouping> {

    override fun get(): String = "$key=${value.v}"

    override fun valid(): Boolean = true

    enum class Grouping(val v: String) {
        USERS("users"),
        PROJECTS("projects"),
    }
} // TODO: Make generic

data class ParamInt(override val key: String, override val value: Int) : Param<Int> {

    override fun get(): String = "$key=$value"

    override fun valid(): Boolean = true

} // TODO: Make generic

data class ParamCalculate(override val key: String, override val value: Calculate) : Param<ParamCalculate.Calculate> {

    override fun get(): String = "$key=${value.v}"

    override fun valid(): Boolean = true

    enum class Calculate(val v: String) {
        TIME("time"),
        EARNINGS("earnings"),
    }
} // TODO: Make generic

data class ParamIds(override val key: String, override val value: List<String>) : Param<List<String>> {
    override fun get(): String = "$key=${value.joinToString(separator = ",")}"

    override fun valid(): Boolean = this.value.isNotEmpty()
}

data class ParamIdsDefault(override val key: String, override val value: List<String> = listOf("0")) :
    Param<List<String>> {
    override fun get(): String = "$key=${value.joinToString(separator = ",")}"

    override fun valid(): Boolean = true
}
