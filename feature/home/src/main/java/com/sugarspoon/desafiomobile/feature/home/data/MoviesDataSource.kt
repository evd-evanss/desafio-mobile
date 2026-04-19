package com.sugarspoon.desafiomobile.feature.home.data

import com.sugarspoon.desafiomobile.feature.home.data.response.MoviesResponse

interface MoviesDataSource {
    suspend fun getMovies(): MoviesResponse
}