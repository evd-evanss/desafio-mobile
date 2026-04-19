package com.sugarspoon.desafiomobile.feature.home.data.response

import com.sugarspoon.desafiomobile.feature.home.domain.model.Movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("items") val items: List<MovieItemResponse>
)

fun MoviesResponse.toDomain() =
    Movies(
        items = items.map { it.toDomain() }
    )