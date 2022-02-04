package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import com.qoiutestapp.chuckjokes.TestStringProvider
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesInteractor
import com.qoiutestapp.chuckjokes.presentation.Communication
import org.junit.Assert.assertEquals
import org.junit.Test

class JokesViewModelTest {

    @Test
    fun incorrectCount() {
        val string = TestStringProvider("error")
        val mapper = JokesDomainToUiMapper(string)
        val result = JokesDomain.Success(listOf("test1"))
        val expected = mutableListOf<JokesUi>()
        val communication = Communication.Test<JokesUi> {
            expected.add(it)
        }
        val model = JokesViewModel(string, TestInteractor(result), mapper, communication)
        val actual = listOf(JokesUi.Error("error"))
        model.fetch("saas")
        assertEquals(expected, actual)
    }

    @Test
    fun negativeCount() {
        val string = TestStringProvider("less than 0")
        val mapper = JokesDomainToUiMapper(string)
        val result = JokesDomain.Success(listOf("test1"))
        val expected = mutableListOf<JokesUi>()
        val communication = Communication.Test<JokesUi> {
            expected.add(it)
        }
        val model = JokesViewModel(string, TestInteractor(result), mapper, communication)
        val actual = listOf(JokesUi.Error("less than 0"))
        model.fetch("-10")
        assertEquals(expected, actual)
    }


    inner class TestInteractor(private val result: JokesDomain) : JokesInteractor {
        override suspend fun fetchJokes(amount: Int): JokesDomain = result
    }
}