package com.sugarspoon.desafiomobile.feature.home.data

import com.sugarspoon.desafiomobile.feature.home.data.response.toDomain
import com.sugarspoon.desafiomobile.feature.home.domain.repositoy.MoviesRepository

class MoviesRepositoryImpl(
    private val dataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun getMovies() =
        dataSource.getMovies().toDomain()
}