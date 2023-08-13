package com.cmonzon.utils

import com.cmonzon.apimodels.http.ContentType

fun ContentType.ktorContentType(): io.ktor.http.ContentType =
    io.ktor.http.ContentType(contentType, contentTypSubType)