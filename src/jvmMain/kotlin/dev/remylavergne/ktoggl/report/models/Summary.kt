package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.common.CurrencyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface Summary

@Serializable
data class SummaryData<T : Summary, R : Summary>(
    val id: Long,
    val title: T,
    val time: Long,
    @SerialName("total_currencies")
    val totalCurrencies: List<CurrencyValue>,
    val items: List<SummaryItem<R>>,
)

@Serializable
data class SummaryItem<R>(
    val title: R,
    val time: Long,
    val cur: CurrencyValue?,
    val sum: Long?,
    val rate: Long?,
)

@Serializable
data class SummaryProject(
    val project: String?,
    val client: String?,
    val color: String?,
    @SerialName("hex_color")
    val hexColor: String?,
) : Summary

@Serializable
data class SummaryTimeEntry(
    @SerialName("time_entry")
    val timeEntry: String,
) : Summary

@Serializable
data class SummaryTask(
    val tasks: String?,
) : Summary

@Serializable
data class SummaryUser(
    val user: String?,
) : Summary

@Serializable
data class SummaryClient(
    val client: String?,
) : Summary
