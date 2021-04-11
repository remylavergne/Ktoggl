package dev.remylavergne.ktoggl.report.common

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyValue(
    val currency: String?,
    val amount: List<Double?>?,
)
