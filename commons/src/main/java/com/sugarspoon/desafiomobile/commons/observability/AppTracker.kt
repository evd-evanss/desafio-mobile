package com.sugarspoon.desafiomobile.commons.observability

import android.util.Log

private const val TAG = "AppTracker"

object AppTracker: Monitor {

    override fun trackEvent(event: String) {
        Log.d(TAG, "UI Event: $event")
    }

    override fun trackNetworkError(
        error: String,
        message: String?
    ) {
        Log.e(
            TAG,
            "Network Error: $error\nMessage: $message"
        )
    }

    override fun trackEvent(
        event: String,
        params: Map<String, String>
    ) {
        Log.d(TAG, "UI Event: $event, Params: $params")
    }
}