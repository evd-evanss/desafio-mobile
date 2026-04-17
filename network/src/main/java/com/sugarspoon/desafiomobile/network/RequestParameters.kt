package com.sugarspoon.desafiomobile.network

interface RequestParameters {
    val path: String
    val headers: Map<String, String>
    val queries: Map<String, String>
    val body: Any?
}

open class ApiRequestParameters(
    override val path: String,
    override val headers: Map<String, String> = emptyMap(),
    override val queries: Map<String, String> = emptyMap(),
    override val body: Any? = null,
) : RequestParameters