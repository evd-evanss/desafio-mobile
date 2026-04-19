package com.sugarspoon.desafiomobile.feature.home.presentation.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import com.sugarspoon.desafiomobile.feature.home.R
import com.sugarspoon.desafiomobile.feature.home.domain.model.MovieItem
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    onNavigateToTrailers: (List<String>) -> Unit
) {

    val viewModel: MoviesViewModel = koinInject<MoviesViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    MoviesScreenContent(
        selectedTab = state.selectedTab,
        movies = state.movies,
        filterMovies = state.filterMovies,
        isError = state.isError,
        isLoading = state.isLoading,
        warning = state.warning,
        onRefresh = viewModel::onRefresh,
        onTabSelected = { viewModel.selectTab(it) },
        onSearchChanged = viewModel::onSearchChanged,
        onNavigateToTrailers = { urls -> onNavigateToTrailers(urls) },
        onDismiss = viewModel::dismissWarningBottomSheet,
        onRetry = viewModel::tryAgain,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesScreenContent(
    selectedTab: String = MoviesTabs.COMING_SOON.tag,
    movies: List<MovieItem> = emptyList(),
    filterMovies: List<MovieItem> = emptyList(),
    isError: Boolean = false,
    isLoading: Boolean = false,
    warning: WarningsType = WarningsType.GENERIC_ERROR,
    onRefresh: () -> Unit = {},
    onTabSelected: (String) -> Unit = {},
    onSearchChanged: (String) -> Unit = {},
    onNavigateToTrailers: (List<String>) -> Unit = {},
    onDismiss: () -> Unit = {},
    onRetry: () -> Unit = {},
) {

    val pullToRefreshState = rememberPullToRefreshState()
    Scaffold { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = pullToRefreshState,
            isRefreshing = isLoading,
            onRefresh = onRefresh,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MovieTypeSelector(
                    tabLeft = stringResource(MoviesTabs.LATEST_PREMIERES.title),
                    tabRight = stringResource(MoviesTabs.COMING_SOON.title),
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected,
                    onSearchChanged = onSearchChanged
                )

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    text = stringResource(R.string.home_topbar_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    if (isLoading && movies.isEmpty()) {
                        items(6) {
                            MovieCardSkeleton()
                        }
                    } else {
                        items(filterMovies) { movie ->
                            MovieCard(
                                modifier = Modifier.clickable {
                                    val urls = movie.trailers?.mapNotNull { it.url } ?: emptyList()
                                    if (urls.isNotEmpty()) {
                                        onNavigateToTrailers(urls)
                                    }
                                },
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
    }

    if (isError) {
        WarningBottomSheet(
            title = stringResource(warning.title),
            description = stringResource(warning.description),
            onDismiss = onDismiss,
            onRetry = onRetry,
        )
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesTheme {
        val movies = mutableListOf<MovieItem>()
        repeat(4) {
            movies.add(
                MovieItem(
                    id = "1",
                    title = "Technoboss",
                    originalTitle = "Technoboss",
                    type = "Ficção",
                    movieIdUrl = "",
                    ancineId = "",
                    imageFeatured = "https://exemplo.com/imagem.jpg",
                    isComingSoon = true,
                    isPlaying = false,
                    premiereDate = null,
                    // Adicione os outros campos como strings vazias ou nulos conforme sua classe
                    countryOrigin = "Brasil",
                    priority = 1,
                    contentRating = "14",
                    duration = "1h 50min",
                    rating = 4.5,
                    synopsis = "Uma sinopse de exemplo aqui...",
                    cast = "Ator A, Atriz B",
                    director = "Diretor X",
                    distributor = "Distribuidora Y",
                    inPreSale = false,
                    isReexhibition = false,
                    urlKey = "technoboss",
                    countIsPlaying = 0,
                    creationDate = "",
                    city = "",
                    siteURL = "",
                    nationalSiteURL = "",
                    images = emptyList(),
                    genres = emptyList(),
                    trailers = emptyList(),
                    ratingDescriptors = emptyList(),
                    accessibilityHubs = emptyList(),
                    completeTags = emptyList(),
                    tags = emptyList(),
                    partnershipType = "",
                    titleSeen = "",
                    ratingDetails = null,
                    b2BEventId = "",
                    cities = emptyList(),
                    directors = null
                )
            )
        }

        MoviesScreenContent(
            filterMovies = movies
        )
    }
}