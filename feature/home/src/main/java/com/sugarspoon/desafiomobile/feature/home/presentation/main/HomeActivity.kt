package com.sugarspoon.desafiomobile.feature.home.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sugarspoon.desafiomobile.ds.theme.MoviesTheme
import com.sugarspoon.desafiomobile.feature.home.presentation.main.routes.HomeDestination
import com.sugarspoon.desafiomobile.feature.home.presentation.main.routes.TrailersDestination
import com.sugarspoon.desafiomobile.feature.home.presentation.components.BottomNavigationBar
import com.sugarspoon.desafiomobile.feature.home.presentation.components.HomeNavigationItem
import com.sugarspoon.desafiomobile.feature.home.presentation.movies.MoviesScreen
import com.sugarspoon.desafiomobile.feature.home.presentation.trailers.TrailersScreen

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val navController = rememberNavController()
                var bottomTabSelected by remember {
                    mutableStateOf(HomeNavigationItem.HOME)
                }
                Scaffold(
                    modifier = Modifier,
                    bottomBar = {
                        BottomNavigationBar(
                            selectedItem = bottomTabSelected,
                            onItemSelected = {
                                bottomTabSelected = it
                                val destination = when (it) {
                                    HomeNavigationItem.HOME -> HomeDestination
                                    HomeNavigationItem.TRAILERS -> TrailersDestination(emptyList())
                                }
                                navController.navigate(destination)
                            },
                        )
                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = HomeDestination
                    ) {
                        composable<HomeDestination> {
                            MoviesScreen(
                                onNavigateToTrailers = { urls ->
                                    navController.navigate(TrailersDestination(urls))
                                }
                            )
                        }

                        composable<TrailersDestination> { backStackEntry ->
                            val trailers: TrailersDestination = backStackEntry.toRoute()
                            TrailersScreen(
                                urls = trailers.urls,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun Context.navigateToHomeActivity() =
            Intent(
                this@navigateToHomeActivity,
                HomeActivity::class.java
            ).apply {
                startActivity(this@apply)
            }
    }
}
