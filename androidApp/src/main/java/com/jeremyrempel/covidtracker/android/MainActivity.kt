package com.jeremyrempel.covidtracker.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.setContent
import com.jeremyrempel.covidtracker.android.ui.MyApp
import com.jeremyrempel.covidtracker.android.ui.MyAppTheme
import com.jeremyrempel.covidtracker.api.MyApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val job = GlobalScope.launch {
            val api = MyApi()
            val result = api.getCurrentData()
            println(result)
        }

        setContent {
            MyAppTheme(isSystemInDarkTheme()) {
                MyApp()
            }
        }
    }
}
