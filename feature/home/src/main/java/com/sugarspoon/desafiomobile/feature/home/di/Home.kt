package com.sugarspoon.desafiomobile.feature.home.di

import org.koin.core.context.loadKoinModules

object Home {
    fun inject() {
        loadKoinModules(moviesModule)
    }
}