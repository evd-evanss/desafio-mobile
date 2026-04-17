package com.sugarspoon.desafiomobile.feature.movies.domain.usecase

import com.sugarspoon.desafiomobile.feature.movies.domain.Resource
import com.sugarspoon.desafiomobile.feature.movies.domain.model.Movies
import com.sugarspoon.desafiomobile.feature.movies.domain.repositoy.MoviesRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val NO_INTERNET_ERROR = "No internet connection"
private const val UNKNOWN_ERROR = "Unknown error"

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): Resource<Movies> {
        return try {
            Resource.Success(repository.getMovies())
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException,
                is SocketTimeoutException -> {
                    return Resource.Error(NO_INTERNET_ERROR)
                }

                else -> Resource.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}
