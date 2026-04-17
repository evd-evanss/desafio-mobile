package com.sugarspoon.desafiomobile.feature.movies.presentation.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sugarspoon.desafiomobile.ds.components.MovieCard
import com.sugarspoon.desafiomobile.ds.components.MovieCardSkeleton
import com.sugarspoon.desafiomobile.ds.components.MovieTypeSelector
import com.sugarspoon.desafiomobile.ds.theme.MoviesTheme
import com.sugarspoon.desafiomobile.feature.movies.R
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            MovieTypeSelector(
                tabLeft = stringResource(MoviesTabs.LATEST_PREMIERES.title),
                tabRight = stringResource(MoviesTabs.COMING_SOON.title),
                selectedTab = state.selectedTab,
                onTabSelected = { viewModel.selectTab(it) },
                onSearchChanged = viewModel::onSearchChanged
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(R.string.movies_topbar_title),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (state.isLoading) {
                    items(6) {
                        MovieCardSkeleton()
                    }
                } else {
                    items(state.filterMovies) { movie ->
                        MovieCard(
                            title = movie.title,
                            url = movie.imageFeatured,
                            badgeText = movie.premiereDate?.dayAndMonth
                                ?: stringResource(MoviesTabs.COMING_SOON.title)
                        )
                    }
                }
            }
        }
    }

    if (state.isError) {
        WarningBottomSheet(
            title = stringResource(state.warning.title),
            description = stringResource(state.warning.description),
            onDismiss = viewModel::dismissWarningBottomSheet,
            onRetry = viewModel::loadMovies,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesTheme {
        MoviesScreen()
    }
}