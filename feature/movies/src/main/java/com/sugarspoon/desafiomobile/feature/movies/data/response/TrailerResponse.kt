package com.sugarspoon.desafiomobile.feature.movies.data.response

import com.sugarspoon.desafiomobile.feature.movies.domain.model.Trailer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    @SerialName("type") val type: String?,
    @SerialName("url") val url: String?,
    @SerialName("embeddedUrl") val embeddedUrl: String?
)

fun TrailerResponse.toDomain() =
    Trailer(
        type = type,
        url = url,
        embeddedUrl = embeddedUrl
    )

fun List<TrailerResponse>?.toDomain() =
    this?.map { it.toDomain() } ?: emptyList()