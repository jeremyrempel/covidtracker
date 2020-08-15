package com.jeremyrempel.covidtracker.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.setContent
import com.jeremyrempel.covidtracker.android.ui.MyApp
import com.jeremyrempel.covidtracker.android.ui.MyAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme(isSystemInDarkTheme()) {
                MyApp()
            }
        }
    }
}
