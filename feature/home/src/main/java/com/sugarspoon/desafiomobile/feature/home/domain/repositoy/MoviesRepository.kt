package com.sugarspoon.desafiomobile.feature.home.domain.repositoy

import com.sugarspoon.desafiomobile.feature.home.domain.model.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
}