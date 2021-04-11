package dev.remylavergne.ktoggl.report.models

import dev.remylavergne.ktoggl.report.common.CurrencyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseTime<T : List<*>>(
    @SerialName("total_grand")
    val totalGrand: Long?,
    @SerialName("total_billable")
    val totalBillable: Long?,
    @SerialName("total_currencies")
    val totalCurrencies: List<CurrencyValue>,
    val data: T,
    @SerialName("week_totals")
    val weekTotals: List<Long?>,
)

@Serializable
data class BaseEarnings<T>(
    @SerialName("total_grand")
    val totalGrand: Long?,
    @SerialName("total_billable")
    val totalBillable: Long?,
    @SerialName("total_currencies")
    val totalCurrencies: List<CurrencyValue>,
    val data: T,
    @SerialName("week_totals")
    val weekTotals: List<CurrencyValue>,
)