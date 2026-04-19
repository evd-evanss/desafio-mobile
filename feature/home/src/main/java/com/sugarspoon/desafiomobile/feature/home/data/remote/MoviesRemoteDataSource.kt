package com.sugarspoon.desafiomobile.feature.home.data.remote

import com.sugarspoon.desafiomobile.feature.home.data.MoviesDataSource
import com.sugarspoon.desafiomobile.feature.home.data.response.MoviesResponse
import com.sugarspoon.desafiomobile.network.ApiClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val MOVIES_URL = "events/coming-soon/partnership/desafio"

class MoviesRemoteDataSource(
    private val api: ApiClient,
) : MoviesDataSource {
    override suspend fun getMovies() =
        api.client
            .get(MOVIES_URL)
            .body<MoviesResponse>()
}