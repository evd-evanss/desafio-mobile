package com.sugarspoon.desafiomobile.network.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sugarspoon.desafiomobile.network.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            engine {
                addInterceptor(ChuckerInterceptor.Builder(androidContext()).build())
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            
            install(Logging) {
                level = LogLevel.BODY
            }

            defaultRequest {
                header("Content-Type", "application/json")
                url("https://api-content.ingresso.com/v0/")
            }
        }
    }
    
    single { ApiClient(get()) }
}
