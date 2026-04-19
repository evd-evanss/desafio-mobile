package com.sugarspoon.desafiomobile

import android.app.Application
import android.content.Context
import com.sugarspoon.desafiomobile.commons.observability.AppTracker
import com.sugarspoon.desafiomobile.feature.home.di.Home
import com.sugarspoon.desafiomobile.network.di.Network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MoviesApp: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        startKoin {
            androidContext(this@MoviesApp)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Network.inject()
        Home.inject()
        AppTracker.trackEvent("Application Started")
    }
}