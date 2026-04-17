package com.sugarspoon.desafiomobile.network.extension

import com.sugarspoon.desafiomobile.network.error.ErrorBodyResponse
import com.sugarspoon.desafiomobile.network.exception.ErrorBodyException
import com.sugarspoon.desafiomobile.network.exception.ErrorBodyParseException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

private val errorJson = Json { ignoreUnknownKeys = true }

/**
 * A extensions to receive a [Throwable] and check if the exception is a [ClientRequestException]
 * after that it will try to parse to a custom Exception [ErrorBodyException]
 * if the throwable is not a typed [ClientRequestException] it will thrown itself
 * if the parser does not succeeded, it will throw a [ErrorBodyParseException] containing inside the exception throws
 */
suspend fun Throwable.parseHttpExceptionOrThrow(): Throwable =
    when (this) {
        is ClientRequestException -> {
            try {
                val httpCode = response.status.value
                val responseBody = response.call.response.bodyAsText()
                val errorBodyResponse = errorJson.decodeFromString<ErrorBodyResponse>(responseBody)
                val message = errorBodyResponse.message

                ErrorBodyException(
                    httpCode = httpCode,
                    message = message.orEmpty(),
                    throwable = this,
                )
            } catch (exception: Exception) {
                ErrorBodyParseException(exception)
            }
        }

        else -> this
    }