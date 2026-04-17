package com.sugarspoon.desafiomobile.feature.movies.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sugarspoon.desafiomobile.ds.theme.MoviesTheme
import com.sugarspoon.desafiomobile.feature.movies.presentation.movies.MoviesScreen

class MoviesActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                MoviesScreen()
            }
        }
    }

    companion object {
        fun Context.navigateToMoviesActivity() =
                Intent(
                    this@navigateToMoviesActivity,
                    MoviesActivity::class.java
                ).apply {
                    startActivity(this@apply)
                }
    }
}