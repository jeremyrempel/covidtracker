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

        return SummaryModel(
            summary.positive,
            summary.positiveIncrease,
            SummaryModel.Change.UP,
            summary.death ?: 0,
            summary.deathIncrease,
            SummaryModel.Change.DOWN,
            summary.recovered ?: 0,
            (summary.death ?: 0).toDouble() / summary.positive.toDouble(),
            SummaryModel.Change.UP,
            summary.hospitalizedCurrently ?: 0,
            SummaryModel.Change.UP,
            summary.lastModified
        )
    }
}
