package com.sugarspoon.desafiomobile.commons.observability

interface Monitor {
    fun trackEvent(
        event: String
    )

    fun trackNetworkError(
        error: String,
        message: String? = null,
    )

    fun trackEvent(
        event: String,
        params: Map<String, String>
    )
}