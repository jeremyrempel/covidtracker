package com.jeremyrempel.covidtracker.summary

import com.jeremyrempel.covidtracker.log
import com.jeremyrempel.covidtracker.summary.api.ApiResult
import com.jeremyrempel.covidtracker.summary.api.MyApi
import com.jeremyrempel.covidtracker.summary.ui.Lce
import com.jeremyrempel.covidtracker.summary.ui.SummaryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SummaryUseCase(
    private val api: MyApi
) {

    fun getSummary(): Flow<Lce<SummaryModel>> {
        return flow {
            try {
                emit(Lce.Loading())
                val uiModel = apiToUiModel(api.getCurrentData())
                emit(Lce.Content(uiModel))
            } catch (e: Throwable) {
                log("Error fetching data", e)
                emit(Lce.Error(e.message ?: "Error"))
            }
        }
    }

    private fun apiToUiModel(apiModel: List<ApiResult>): SummaryModel {

        val summary = apiModel[0]

        val positiveMovingAvg = apiModel
            .take(30)
            .map { it.positiveIncrease }
            .average()
        val positiveChange = when {
            summary.positiveIncrease > positiveMovingAvg -> SummaryModel.Change.UP
            summary.positiveIncrease < positiveMovingAvg -> SummaryModel.Change.DOWN
            else -> SummaryModel.Change.NOCHANGE
        }

        val deathMovingAvg = apiModel
            .take(30)
            .map { it.deathIncrease }
            .average()
        val deathChange = when {
            summary.deathIncrease > deathMovingAvg -> SummaryModel.Change.UP
            summary.deathIncrease < deathMovingAvg -> SummaryModel.Change.DOWN
            else -> SummaryModel.Change.NOCHANGE
        }

        val currentlyHospitalizedAvg = apiModel
            .take(30)
            .map { it.hospitalizedIncrease ?: 0 }
            .average()
        val hospitializedChange = when {
            summary.hospitalizedCurrently ?: 0 > currentlyHospitalizedAvg -> SummaryModel.Change.UP
            summary.hospitalizedCurrently ?: 0 < currentlyHospitalizedAvg -> SummaryModel.Change.DOWN
            else -> SummaryModel.Change.NOCHANGE
        }

        val deathRateMovingAvg = apiModel
            .take(30)
            .map { (summary.death ?: 0).toDouble() / summary.positive.toDouble() }
            .average()
        val currentDeathRate = (summary.death ?: 0).toDouble() / summary.positive.toDouble()
        val deathRateChange = when {
            currentDeathRate > deathRateMovingAvg -> SummaryModel.Change.UP
            currentDeathRate < deathRateMovingAvg -> SummaryModel.Change.DOWN
            else -> SummaryModel.Change.NOCHANGE
        }

        return SummaryModel(
            summary.positive,
            summary.positiveIncrease,
            positiveChange,
            summary.death ?: 0,
            summary.deathIncrease,
            deathChange,
            summary.recovered ?: 0,
            currentDeathRate,
            deathRateChange,
            summary.hospitalizedCurrently ?: 0,
            hospitializedChange,
            summary.lastModified
        )
    }
}
