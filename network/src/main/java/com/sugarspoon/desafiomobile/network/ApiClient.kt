package com.sugarspoon.desafiomobile.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

class ApiClient(
    val client: HttpClient
) {
    @PublishedApi
    internal suspend inline fun <reified T : Any> request(
        requestParameters: RequestParameters,
        httpMethod: HttpMethod
    ): T =
        client.request {
            method = httpMethod

            url(requestParameters.path) {
                requestParameters.queries.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }

            headers {
                requestParameters.headers.forEach { (key, value) ->
                    append(key, value)
                }
            }

            requestParameters.body?.let {
                contentType(ContentType.Application.Json)
                setBody(it)
            }
        }.body()

    suspend inline fun <reified T : Any> get(parameter: RequestParameters): T = request(parameter, HttpMethod.Get)

    suspend inline fun <reified T : Any> post(parameter: RequestParameters): T = request(parameter, HttpMethod.Post)

    suspend inline fun <reified T : Any> put(parameter: RequestParameters): T = request(parameter, HttpMethod.Put)

    suspend inline fun <reified T : Any> delete(parameter: RequestParameters): T = request(parameter, HttpMethod.Delete)

    suspend inline fun <reified T : Any> patch(parameter: RequestParameters): T = request(parameter, HttpMethod.Patch)
}


