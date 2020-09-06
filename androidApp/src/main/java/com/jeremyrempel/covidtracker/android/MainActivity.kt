package com.jeremyrempel.covidtracker.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.setContent
import com.jeremyrempel.covidtracker.android.ui.MyApp
import com.jeremyrempel.covidtracker.android.ui.MyAppTheme
import com.jeremyrempel.covidtracker.summary.SummaryUseCase
import com.jeremyrempel.covidtracker.summary.api.MyApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val summaryUseCase = SummaryUseCase(api)
        val uiModelFlow = summaryUseCase.getSummary()

        setContent {
            MyAppTheme(isSystemInDarkTheme()) {
                MyApp(uiModelFlow)
            }
        }
    }
}
