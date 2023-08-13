package com.cmonzon.apimodels.http


data class ContentType(
    val contentType: String,
    val contentTypSubType: String
)

object ApiVersion {
    object V1 {
        val protobuf = ContentType("application", "vnd.consumer.mobile-v1+protobuf")
        val json = ContentType("application", "vnd.consumer.mobile-v1+json")
    }

    object V2 {
        val protobuf = ContentType("application", "vnd.consumer.mobile-v2+protobuf")
    }
}