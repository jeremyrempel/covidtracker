package com.jeremyrempel.covidtracker

import android.util.Log

actual fun log(msg: String, e: Throwable) {
    Log.e("Main", "API Error", e)
}
