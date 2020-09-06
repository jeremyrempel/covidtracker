package com.jeremyrempel.covidtracker.summary.ui

import kotlinx.datetime.Instant

data class SummaryModel(
    val totalCase: Int,
    val newDailyCases: Int,
    val newDailyCasesChange: Change,
    val totalDeaths: Int,
    val newDailyDeaths: Int,
    val newDailyDeathsChange: Change,
    val totalRecovered: Int,
    val deathRate: Double,
    val deathRateChange: Change,
    val currentlyHospitalized: Int,
    val currentlyHospitalizedChange: Change,
    val lastModified: Instant
) {
    enum class Change { UP, DOWN, NOCHANGE }
}
