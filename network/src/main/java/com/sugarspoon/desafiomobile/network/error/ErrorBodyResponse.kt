package com.sugarspoon.desafiomobile.network.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorBodyResponse(
    @SerialName("error") val message: String? = null,
    @SerialName("code") val code: String? = null
)