package com.jeremyrempel.covidtracker.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.setContent
import com.jeremyrempel.covidtracker.android.ui.MyApp
import com.jeremyrempel.covidtracker.android.ui.MyAppTheme
import com.jeremyrempel.covidtracker.api.MyApi
import com.jeremyrempel.covidtracker.ui.Lce
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uiStateFlow = flow {
            try {
                val api = MyApi()
                val uiModel = api.getCurrentData()[0]
                emit(Lce.Content(uiModel))
            } catch (e: Throwable) {
                // todo log
                emit(Lce.Error(e.message ?: "Unknown Error"))
            }
        }

        setContent {
            MyAppTheme(isSystemInDarkTheme()) {
                MyApp(uiStateFlow)
            }
        }
    }
}
