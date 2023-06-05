package com.cmonzon.apimodels

import kotlinx.serialization.Serializable

@Serializable
data class UpcomingMoviesDto(
    val page: Int,
    val results: List<MovieDto>
)