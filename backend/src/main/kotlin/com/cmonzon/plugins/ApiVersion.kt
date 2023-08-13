package com.cmonzon.plugins

import com.cmonzon.apimodels.http.ApiVersion
import com.cmonzon.utils.ktorContentType
import io.ktor.server.routing.*

fun Route.v1Route(build: Route.() -> Unit): Route {
    return accept(
        contentTypes = arrayOf(ApiVersion.V1.protobuf.ktorContentType()),
        build = build
    )
}

fun Route.v2Route(build: Route.() -> Unit): Route {
    return accept(
        contentTypes = arrayOf(ApiVersion.V1.json.ktorContentType()),
        build = build
    )
}