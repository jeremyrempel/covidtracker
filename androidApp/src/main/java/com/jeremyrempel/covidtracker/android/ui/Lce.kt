package com.jeremyrempel.covidtracker.android.ui

sealed class Lce<T> {
    class Loading<T> : Lce<T>()
    class Error<T>(val msg: String) : Lce<T>()
    data class Content<T>(val data: T) : Lce<T>()
}
