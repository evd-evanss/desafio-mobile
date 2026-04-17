package com.sugarspoon.desafiomobile.feature.movies.domain.model

data class PremiereDate(
    val localDate: String?,
    val isToday: Boolean,
    val dayOfWeek: String?,
    val dayAndMonth: String?,
    val hour: String?,
    val year: String?
)