package com.sugarspoon.desafiomobile.feature.movies.domain.repositoy

import com.sugarspoon.desafiomobile.feature.movies.domain.model.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
}