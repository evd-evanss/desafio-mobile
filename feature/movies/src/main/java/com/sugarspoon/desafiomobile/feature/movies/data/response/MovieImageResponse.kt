package com.sugarspoon.desafiomobile.feature.movies.data.response

import com.sugarspoon.desafiomobile.feature.movies.domain.model.MovieImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieImageResponse(
    @SerialName("url") val url: String?,
    @SerialName("type") val type: String?
)

fun MovieImageResponse.toDomain() =
    MovieImage(
        url = url,
        type = type
    )
