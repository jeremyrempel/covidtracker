package com.jeremyrempel.covidtracker.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

/**
 * https://api.covidtracking.com/v1/us/current.json
 */
class MyApi(
    private val scheme: String = "https",
    private val host: String = "api.covidtracking.com"

) {

    private val client = HttpClient {

        install(JsonFeature) {
            val config = Json.Default
            serializer = KotlinxSerializer(config)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    // todo implement logging
                    println("Network: $message")
                }
            }

            level = LogLevel.INFO
        }
    }

    suspend fun getCurrentData(): List<ApiResult> {
        return client.get(
            scheme = scheme,
            host = host,
            path = "/v1/us/current.json"
        )
    }
}
