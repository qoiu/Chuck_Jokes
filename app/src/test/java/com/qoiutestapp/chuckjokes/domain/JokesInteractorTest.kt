package com.qoiutestapp.chuckjokes.domain

import com.qoiutestapp.chuckjokes.data.jokes.JokesData
import com.qoiutestapp.chuckjokes.data.jokes.JokesDataToDomainMapper
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class JokesInteractorTest {


    private val mapper = JokesDataToDomainMapper()

    @Test
    fun success() = runBlockingTest {
        val list = listOf(
            JokesData.JokeCloud(1, "test1"),
            JokesData.JokeCloud(2, "test2")
        )
        val actual = JokesDomain.Success(listOf("test1", "test2"))
        val expected = JokesData.JokesResult("success", list)
        assertEquals(
            JokesInteractor.Base(TestRepository(expected), mapper).fetchJokes(1),
            actual
        )
    }

    inner class TestRepository(private val actual: JokesData) : Repository<JokesData> {
        override suspend fun fetchData(data: Int) = actual
    }
}