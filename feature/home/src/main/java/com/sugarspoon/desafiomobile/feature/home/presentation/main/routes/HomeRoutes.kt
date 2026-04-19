package com.sugarspoon.desafiomobile.feature.home.presentation.main.routes

import kotlinx.serialization.Serializable

@Serializable
object HomeDestination: Destination

@Serializable
data class TrailersDestination(
    val urls: List<String>
): Destination

interface Destination