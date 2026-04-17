package com.sugarspoon.desafiomobile.feature.movies.data.response

import com.sugarspoon.desafiomobile.feature.movies.domain.model.RatingDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDetailsResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?,
    @SerialName("label") val label: String?,
    @SerialName("displayName") val displayName: String?,
    @SerialName("description") val description: String?,
    @SerialName("color") val color: String?
)

fun RatingDetailsResponse.toDomain() =
    RatingDetails(
        id = id,
        name = name,
        label = label,
        displayName = displayName,
        description = description,
        color = color
    )