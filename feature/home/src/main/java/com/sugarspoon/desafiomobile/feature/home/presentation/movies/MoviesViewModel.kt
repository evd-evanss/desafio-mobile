package com.sugarspoon.desafiomobile.feature.home.presentation.movies

import androidx.lifecycle.viewModelScope
import com.sugarspoon.desafiomobile.commons.StateViewModel
import com.sugarspoon.desafiomobile.commons.UiState
import com.sugarspoon.desafiomobile.commons.observability.AppTracker
import com.sugarspoon.desafiomobile.feature.home.domain.Resource
import com.sugarspoon.desafiomobile.feature.home.domain.model.MovieItem
import com.sugarspoon.desafiomobile.feature.home.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

private const val NO_INTERNET_ERROR = "No internet connection"

class MoviesViewModel(
    private val getMovies: GetMoviesUseCase
) : StateViewModel<MoviesState>(MoviesState()) {

    init {
        loadMovies()
        AppTracker.trackEvent("viu: tela de filmes")
    }

    fun tryAgain() {
        AppTracker.trackEvent("clicou: tentar novamente")
        loadMovies()
    }

    fun onRefresh() {
        AppTracker.trackEvent("pushou: atualizar")
        setState { it.clear() }
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            setState { it.copy(isLoading = true) }
            when (val result = getMovies()) {
                is Resource.Success -> {
                    setState { it.setMovies(result.data.items) }
                    AppTracker.trackEvent("carregou filmes")
                }
                is Resource.Error -> {
                    val warningType = if (result.message == NO_INTERNET_ERROR) {
                        WarningsType.NO_INTERNET_ERROR
                    } else {
                        WarningsType.GENERIC_ERROR
                    }
                    setState {
                        it.showWarning(
                            isError = true,
                            warning = warningType,
                        )
                    }
                    AppTracker.trackEvent("viu erro: ${result.message}")
                }
            }
            setState { it.copy(isLoading = false) }
        }
    }

    fun dismissWarningBottomSheet() {
        setState { it.copy(isError = false) }
    }

    fun selectTab(tab: String) {
        setState {
            it.filterMovies(
                selectedTab = tab,
            )
        }
        AppTracker.trackEvent(
            "clicou: ${tab.lowercase()}",
        )
    }

    fun onSearchChanged(query: String) {
        setState {
            it.copy(
                filterMovies = it.movies.filter { movie ->
                    movie.title.contains(query, ignoreCase = true)
                }
            )
        }
    }
}

data class MoviesState(
    val selectedTab: String = MoviesTabs.COMING_SOON.tag,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val movies: List<MovieItem> = emptyList(),
    val filterMovies: List<MovieItem> = emptyList(),
    val tabs: MoviesTabs = MoviesTabs.COMING_SOON,
    val warning: WarningsType = WarningsType.GENERIC_ERROR,
) : UiState {
    fun setMovies(
        movies: List<MovieItem>,
    ) = copy(
        movies = movies,
        filterMovies = movies,
    )

    fun showWarning(
        isError: Boolean,
        warning: WarningsType,
    ) = copy(
        isError = isError,
        warning = warning,
    )

    fun filterMovies(
        selectedTab: String,
    ): MoviesState {
        val filterMovies = when (selectedTab) {
            MoviesTabs.COMING_SOON.tag -> {
                movies.filter {
                    it.isComingSoon
                }.sortedBy {
                    it.premiereDate?.localDate ?: ""
                }
            }

            else -> {
                movies.filter {
                    it.isPlaying
                }.sortedByDescending {
                    it.premiereDate?.localDate ?: ""
                }
            }
        }
        return copy(
            selectedTab = selectedTab,
            filterMovies = filterMovies,
        )
    }

    fun clear() =
        copy(
            selectedTab = MoviesTabs.COMING_SOON.tag,
            isError = false,
            isLoading = false,
            movies = emptyList(),
            filterMovies = emptyList(),
            tabs = MoviesTabs.COMING_SOON
        )
}