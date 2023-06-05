package com.cmonzon.rest_protobuf

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform