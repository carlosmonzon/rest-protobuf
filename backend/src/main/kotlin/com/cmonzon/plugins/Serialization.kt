package com.cmonzon.plugins

import com.cmonzon.apimodels.UpcomingMoviesDto
import io.ktor.serialization.kotlinx.protobuf.protobuf
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        protobuf(ProtoBuf {
            encodeDefaults = true
        })
    }
    routing {
        get("/movie/upcoming") {
            val upcomingMovies = FileReader.readJsonFile()
            if (upcomingMovies != null) {
                call.respond(upcomingMovies)
            } else {
                call.respond("Empty")
            }
        }
    }
}

object FileReader {
    val json = Json { ignoreUnknownKeys = true }
    fun readJsonFile(): UpcomingMoviesDto? {
        val fileContent =
            this::class.java.classLoader.getResource("upcomingMovies.json")?.readText()
        return fileContent?.let {
            return json.decodeFromString<UpcomingMoviesDto>(it)
        }
    }
}
