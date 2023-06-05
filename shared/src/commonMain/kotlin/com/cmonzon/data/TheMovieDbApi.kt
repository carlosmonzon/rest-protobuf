package com.cmonzon.data

import com.cmonzon.apimodels.UpcomingMoviesDto
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.protobuf.protobuf
import kotlinx.serialization.protobuf.ProtoBuf

class TheMovieDbApi {
    private val client = HttpClient {
        expectSuccess = true
        responseValidator()
        install(ContentNegotiation) {
            protobuf(ProtoBuf {
                encodeDefaults = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }.also { Napier.base(DebugAntilog()) }

    suspend fun getUpcoming() = client.safeRequest<UpcomingMoviesDto, Any> {
        method = HttpMethod.Get
        url("http://10.0.2.2:8080/movie/upcoming")
    }
}