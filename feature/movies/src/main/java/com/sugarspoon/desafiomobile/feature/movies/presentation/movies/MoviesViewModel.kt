package com.sugarspoon.desafiomobile.feature.movies.presentation.movies

import androidx.lifecycle.viewModelScope
import com.sugarspoon.desafiomobile.commons.StateViewModel
import com.sugarspoon.desafiomobile.commons.UiState
import com.sugarspoon.desafiomobile.feature.movies.domain.Resource
import com.sugarspoon.desafiomobile.feature.movies.domain.model.MovieItem
import com.sugarspoon.desafiomobile.feature.movies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

private const val NO_INTERNET_ERROR = "No internet connection"

class MoviesViewModel(
    private val getMovies: GetMoviesUseCase
) : StateViewModel<MoviesState>(MoviesState()) {

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            setState { it.copy(isLoading = true) }
            when (val result = getMovies()) {
                is Resource.Success -> {
                    setState { it.setMovies(result.data.items) }
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
}