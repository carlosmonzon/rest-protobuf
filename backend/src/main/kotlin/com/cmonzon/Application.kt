package com.cmonzon

import com.cmonzon.apimodels.http.ApiVersion
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.cmonzon.plugins.*
import com.cmonzon.utils.ktorContentType
import io.ktor.serialization.kotlinx.json.*
import io.ktor.serialization.kotlinx.protobuf.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    contentNegotiationSetup()
    configureSerialization()
    configureRouting()
}

@OptIn(ExperimentalSerializationApi::class)
@KtorDsl
fun Application.contentNegotiationSetup() {
    install(ContentNegotiation) {
        protobuf(
            ProtoBuf {
                encodeDefaults = true
            },
            contentType = ApiVersion.V1.protobuf.ktorContentType()
        )
        protobuf(
            ProtoBuf {
                encodeDefaults = true
            },
            contentType = ApiVersion.V2.protobuf.ktorContentType()
        )
        json(
            contentType = ApiVersion.V1.json.ktorContentType()
        )
    }
}
