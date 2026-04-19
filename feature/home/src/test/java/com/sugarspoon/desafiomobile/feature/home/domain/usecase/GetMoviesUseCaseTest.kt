package com.sugarspoon.desafiomobile.feature.home.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.sugarspoon.desafiomobile.feature.home.domain.Resource
import com.sugarspoon.desafiomobile.feature.home.domain.model.Movies
import com.sugarspoon.desafiomobile.feature.home.domain.repositoy.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class GetMoviesUseCaseTest {
    private val repository: MoviesRepository = mockk()
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setup() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `execute should return Success when repository returns movies data`() = runTest {
        // GIVEN
        val expectedMovies = Movies(items = emptyList())
        coEvery { repository.getMovies() } returns expectedMovies

        // WHEN
        val result = useCase()

        // THEN
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat((result as Resource.Success).data).isEqualTo(expectedMovies)
    }

    @Test
    fun `execute should return Resource Error with No Internet message when network is off`() = runTest {
        // GIVEN
        coEvery { repository.getMovies() } throws UnknownHostException()

        // WHEN
        val result = useCase()

        // THEN
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat((result as Resource.Error).message).isEqualTo("No internet connection")
    }

    @Test
    fun `execute should return Resource Error with generic message when repository fails`() = runTest {
        // GIVEN
        val genericErrorMessage = "Server Error"
        coEvery { repository.getMovies() } throws Exception(genericErrorMessage)

        // WHEN
        val result = useCase()

        // THEN
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat((result as Resource.Error).message).isEqualTo(genericErrorMessage)
    }
}