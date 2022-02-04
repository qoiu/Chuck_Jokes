package com.qoiutestapp.chuckjokes.data

import com.qoiutestapp.chuckjokes.data.jokes.JokesData
import com.qoiutestapp.chuckjokes.data.jokes.JokesService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BaseJokesRepositoryTest {

    @Test
    fun fetchData() = runBlocking {
        val result = JokesData.JokesResult("success", listOf(JokesData.JokeCloud(1, "joke")))
        val service = TestService(result)
        val actual = BaseJokesRepository(service).fetchData(1)
        assertEquals(result, actual)
    }

    @Test
    fun fetchError() = runBlocking {
        val e = IllegalStateException("someError")
        val service = TestServiceError(e)
        val actual = BaseJokesRepository(service).fetchData(1)
        val expected = JokesData.Fail(e)
        assertEquals(expected, actual)
    }

    inner class TestServiceError(private val e: Exception) : JokesService {
        override suspend fun fetchRandomJokes(amount: Int): JokesData.JokesResult {
            throw e
        }
    }

    inner class TestService(private val result: JokesData.JokesResult) : JokesService {
        override suspend fun fetchRandomJokes(amount: Int) = result
    }
}