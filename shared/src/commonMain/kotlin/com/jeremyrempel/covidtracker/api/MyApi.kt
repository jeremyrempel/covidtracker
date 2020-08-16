package com.jeremyrempel.covidtracker.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * https://api.covidtracking.com/v1/us/current.json
 */
class MyApi(
    private val client: HttpClient = HttpClient(),
    private val scheme: String = "https",
    private val host: String = "api.covidtracking.com"

) {
    suspend fun getCurrentData(): ApiResult {
        return client.get(
            scheme = scheme,
            host = host,
            path = "/v1/us/current.json"
        )
    }
}
