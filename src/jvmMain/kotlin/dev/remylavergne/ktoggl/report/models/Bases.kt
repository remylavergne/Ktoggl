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

@Serializable
data class BaseDetails(
    @SerialName("total_grand")
    val totalGrand: Long?,
    @SerialName("total_billable")
    val totalBillable: Long?,
    @SerialName("total_currencies")
    val totalCurrencies: List<CurrencyValue>,
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("per_page")
    val perPage: Int,
    val data: List<TimeEntry>,
)

@Serializable
data class BaseSummaryProjects<T : Summary, R : Summary>(
    @SerialName("total_grand")
    val totalGrand: Long?,
    @SerialName("total_billable")
    val totalBillable: Long?,
    @SerialName("total_currencies")
    val totalCurrencies: List<CurrencyValue>,
    val data: List<SummaryData<T, R>>,
)