package com.cmonzon.rest_protobuf

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Rest Protobuf from ${platform.name}!"
    }
}