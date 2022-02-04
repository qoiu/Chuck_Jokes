package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import com.qoiutestapp.chuckjokes.TestStringProvider
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import org.junit.Assert.*
import org.junit.Test

class JokesDomainToUiMapperTest {

    @Test
    fun success() {
        val list = listOf("joke1", "joke2")
        val actual =
            JokesDomain.Success(list).map(JokesDomainToUiMapper(TestStringProvider("test")))
        val expected = JokesUi.Success(list)
        assertEquals(expected, actual)
    }

    @Test
    fun fail() {
        for (e in JokesDomain.ErrorType.values()) {
            val actual = JokesDomain.Error(e).map(JokesDomainToUiMapper(TestStringProvider(e.name)))
            val expected = JokesUi.Error(e.name)
            assertEquals(expected, actual)
        }
    }
}