package com.jeremyrempel.covidtracker.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
    val date: Int,
    val dateChecked: String,
    val death: Int,
    val deathIncrease: Int,
    val hash: String,
    val hospitalized: Int,
    val hospitalizedCumulative: Int,
    val hospitalizedCurrently: Int,
    val hospitalizedIncrease: Int,
    val inIcuCumulative: Int,
    val inIcuCurrently: Int,
    val lastModified: String, // todo use kotlinx.date
    val negative: Int,
    val negativeIncrease: Int,
    val onVentilatorCumulative: Int,
    val onVentilatorCurrently: Int,
    val pending: Int,
    val posNeg: Int,
    val positive: Int,
    val positiveIncrease: Int,
    val recovered: Int,
    val states: Int,
    val total: Int,
    val totalTestResults: Int,
    val totalTestResultsIncrease: Int
)
