package com.cmonzon.data

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

fun HttpClientConfig<*>.responseValidator() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, request ->
            val clientException =
                exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
            val exceptionResponse = clientException.response
            if (exceptionResponse.status == HttpStatusCode.Unauthorized) {
                val exceptionResponseText = exceptionResponse.bodyAsText()
                throw UnauthorizedException(exceptionResponse, exceptionResponseText)
            }
        }
    }
}

class UnauthorizedException(response: HttpResponse, cachedResponseText: String) :
    ResponseException(response, cachedResponseText) {
    override val message: String = "Unauthorized request: ${response.call.request.url}. " +
            "Status: ${response.status}."
}