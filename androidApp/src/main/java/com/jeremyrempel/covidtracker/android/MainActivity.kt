package com.jeremyrempel.covidtracker.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.setContent
import com.example.composetest.BuildConfig
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
                if (BuildConfig.DEBUG) {
                    Log.e("Main", "API Error", e)
                }
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
