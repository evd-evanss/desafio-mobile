package com.sugarspoon.desafiomobile.feature.movies.data

import com.sugarspoon.desafiomobile.feature.movies.data.response.toDomain
import com.sugarspoon.desafiomobile.feature.movies.domain.repositoy.MoviesRepository

class MoviesRepositoryImpl(
    private val dataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun getMovies() =
        dataSource.getMovies().toDomain()
}