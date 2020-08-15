package com.jeremyrempel.covidtracker.android.api

import io.ktor.client.HttpClient

/**
 * https://api.covidtracking.com/v1/us/current.json
 */
class MyApi(val client: HttpClient = HttpClient()) {

    suspend fun getDataFromNetwork() {

    }
}
