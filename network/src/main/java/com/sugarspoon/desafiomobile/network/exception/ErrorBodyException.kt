package com.sugarspoon.desafiomobile.network.exception

class ErrorBodyException(
    val httpCode: Int,
    override val message: String,
    throwable: Throwable? = null
) : Exception(message, throwable)