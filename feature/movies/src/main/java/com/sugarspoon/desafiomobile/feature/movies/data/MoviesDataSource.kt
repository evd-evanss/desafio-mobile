package com.sugarspoon.desafiomobile.feature.movies.data

import com.sugarspoon.desafiomobile.feature.movies.data.response.MoviesResponse

interface MoviesDataSource {
    suspend fun getMovies(): MoviesResponse
}