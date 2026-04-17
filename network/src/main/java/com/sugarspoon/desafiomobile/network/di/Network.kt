package com.sugarspoon.desafiomobile.network.di

import org.koin.core.context.loadKoinModules

object Network {

    fun inject() {
        loadKoinModules(networkModule)
    }
}