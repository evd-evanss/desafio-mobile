package com.sugarspoon.desafiomobile.feature.movies.data.response

import com.sugarspoon.desafiomobile.feature.movies.domain.model.PremiereDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PremiereDateResponse(
    @SerialName("localDate") val localDate: String?,
    @SerialName("isToday") val isToday: Boolean,
    @SerialName("dayOfWeek") val dayOfWeek: String?,
    @SerialName("dayAndMonth") val dayAndMonth: String?,
    @SerialName("hour") val hour: String?,
    @SerialName("year") val year: String?
)

fun PremiereDateResponse.toDomain() =
    PremiereDate(
        localDate = localDate,
        isToday = isToday,
        dayOfWeek = dayOfWeek,
        dayAndMonth = dayAndMonth,
        hour = hour,
        year = year
    )