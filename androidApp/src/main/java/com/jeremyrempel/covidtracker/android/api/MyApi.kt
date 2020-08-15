package com.jeremyrempel.covidtracker.android.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * https://api.covidtracking.com/v1/us/current.json
 */
class MyApi(
    private val client: HttpClient = HttpClient(),
    private val scheme: String = "https",
    private val host: String = "api.covidtracking.com",
    private val path: String = "/v1/us/current.json"

) {
    suspend fun getDataFromNetwork(): ApiResult {
        return client.get(
            scheme = scheme,
            host = host,
            path = path
        )
    }
}
