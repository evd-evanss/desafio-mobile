package com.sugarspoon.desafiomobile.feature.movies.presentation.movies

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sugarspoon.desafiomobile.feature.movies.domain.Resource
import com.sugarspoon.desafiomobile.feature.movies.domain.model.MovieItem
import com.sugarspoon.desafiomobile.feature.movies.domain.model.Movies
import com.sugarspoon.desafiomobile.feature.movies.domain.usecase.GetMoviesUseCase
import com.sugarspoon.desafiomobile.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getMoviesUseCase: GetMoviesUseCase = mockk()
    private lateinit var viewModel: MoviesViewModel

    private fun initViewModel() {
        viewModel = MoviesViewModel(getMoviesUseCase)
    }

    @Test
    fun `loadMovies should update state to success when use case returns data`() = runTest {
        // GIVEN
        val mockMovies = Movies(items = emptyList())
        coEvery { getMoviesUseCase() } returns Resource.Success(mockMovies)

        // WHEN
        initViewModel()

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.movies).isEmpty()
            assertThat(state.isLoading).isFalse()
            assertThat(state.isError).isFalse()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadMovies should update state to error when use case returns generic error`() = runTest {
        // GIVEN
        coEvery { getMoviesUseCase() } returns Resource.Error("Some error")

        // WHEN
        initViewModel()

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.isError).isTrue()
            assertThat(state.warning).isEqualTo(WarningsType.GENERIC_ERROR)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadMovies should update state to no internet error when use case returns internet message`() = runTest {
        // GIVEN
        coEvery { getMoviesUseCase() } returns Resource.Error("No internet connection")

        // WHEN
        initViewModel()

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.isError).isTrue()
            assertThat(state.warning).isEqualTo(WarningsType.NO_INTERNET_ERROR)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `dismissWarningBottomSheet should set isError to false`() = runTest {
        // GIVEN
        coEvery { getMoviesUseCase() } returns Resource.Error("No internet connection")
        initViewModel()

        // WHEN
        viewModel.dismissWarningBottomSheet()

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.isError).isFalse()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `selectTab should update selectedTab and filter movies correctly for COMING_SOON`() = runTest {
        // GIVEN
        val comingSoonMovie = mockk<MovieItem> {
            every { isComingSoon } returns true
            every { isPlaying } returns false
            every { premiereDate } returns null
        }
        val nowPlayingMovie = mockk<MovieItem> {
            every { isComingSoon } returns false
            every { isPlaying } returns true
            every { premiereDate } returns null
        }

        val mockMovies = Movies(items = listOf(comingSoonMovie, nowPlayingMovie))
        coEvery { getMoviesUseCase() } returns Resource.Success(mockMovies)

        initViewModel()
        val newTab = MoviesTabs.COMING_SOON.tag

        // WHEN
        viewModel.selectTab(newTab)

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.selectedTab).isEqualTo(newTab)
            assertThat(state.filterMovies).containsExactly(comingSoonMovie)
            assertThat(state.filterMovies).doesNotContain(nowPlayingMovie)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `selectTab should update selectedTab and filter movies correctly for LATEST_PREMIERES`() = runTest {
        // GIVEN
        val comingSoonMovie = mockk<MovieItem> {
            every { isComingSoon } returns true
            every { isPlaying } returns false
            every { premiereDate } returns null
        }
        val nowPlayingMovie = mockk<MovieItem> {
            every { isComingSoon } returns false
            every { isPlaying } returns true
            every { premiereDate } returns null
        }

        val mockMovies = Movies(items = listOf(comingSoonMovie, nowPlayingMovie))
        coEvery { getMoviesUseCase() } returns Resource.Success(mockMovies)

        initViewModel()
        val newTab = MoviesTabs.LATEST_PREMIERES.tag

        // WHEN
        viewModel.selectTab(newTab)

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.selectedTab).isEqualTo(newTab)
            assertThat(state.filterMovies).containsExactly(nowPlayingMovie)
            assertThat(state.filterMovies).doesNotContain(comingSoonMovie)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchChanged should filter movies by title ignoring case`() = runTest {
        // GIVEN
        val movie1 = mockk<MovieItem> { every { title } returns "Batman" }
        val movie2 = mockk<MovieItem> { every { title } returns "Spider-Man" }
        val movie3 = mockk<MovieItem> { every { title } returns "The Dark Knight" }

        val mockMovies = Movies(items = listOf(movie1, movie2, movie3))
        coEvery { getMoviesUseCase() } returns Resource.Success(mockMovies)

        viewModel = MoviesViewModel(getMoviesUseCase)

        val searchQuery = "baT"

        // WHEN
        viewModel.onSearchChanged(searchQuery)

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.filterMovies).containsExactly(movie1)
            assertThat(state.filterMovies).doesNotContain(movie2)
            assertThat(state.filterMovies).doesNotContain(movie3)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchChanged should return empty list when no movie matches the query`() = runTest {
        // GIVEN
        val movie1 = mockk<MovieItem> { every { title } returns "Batman" }
        val mockMovies = Movies(items = listOf(movie1))
        coEvery { getMoviesUseCase() } returns Resource.Success(mockMovies)

        viewModel = MoviesViewModel(getMoviesUseCase)
        val searchQuery = "Avengers"

        // WHEN
        viewModel.onSearchChanged(searchQuery)

        // THEN
        viewModel.state.test {
            val state = awaitItem()
            assertThat(state.filterMovies).isEmpty()
            cancelAndIgnoreRemainingEvents()
        }
    }
}