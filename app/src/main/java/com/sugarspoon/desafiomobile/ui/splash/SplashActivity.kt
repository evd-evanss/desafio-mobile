package com.sugarspoon.desafiomobile.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sugarspoon.desafiomobile.ds.theme.MoviesTheme
import com.sugarspoon.desafiomobile.feature.movies.presentation.main.MoviesActivity.Companion.navigateToMoviesActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        MoviesSplashScreen {
                            navigateToMoviesActivity()
                        }
                    }
                }
            }
        }
    }
}