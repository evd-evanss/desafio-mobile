package com.sugarspoon.desafiomobile.feature.movies.di

import com.sugarspoon.desafiomobile.feature.movies.data.MoviesDataSource
import com.sugarspoon.desafiomobile.feature.movies.data.MoviesRepositoryImpl
import com.sugarspoon.desafiomobile.feature.movies.data.remote.MoviesRemoteDataSource
import com.sugarspoon.desafiomobile.feature.movies.domain.repositoy.MoviesRepository
import com.sugarspoon.desafiomobile.feature.movies.domain.usecase.GetMoviesUseCase
import com.sugarspoon.desafiomobile.feature.movies.presentation.movies.MoviesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val dataModule =
    module {
        factory<MoviesDataSource> { MoviesRemoteDataSource(get()) }
        factory <MoviesRepository>{ MoviesRepositoryImpl(get()) }
    }

internal val domainModule =
    module {
        factory { GetMoviesUseCase(get()) }
    }

internal val presentationModule =
    module {
        viewModel {
            MoviesViewModel(get())
        }
    }

internal val moviesModule: List<Module> =
    listOf(
        dataModule,
        domainModule,
        presentationModule
    )