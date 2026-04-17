package com.sugarspoon.desafiomobile.feature.movies.di

import org.koin.core.context.loadKoinModules

object Movies {
    fun inject() {
        loadKoinModules(moviesModule)
    }
}