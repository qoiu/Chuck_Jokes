package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.domain.JokesDomain
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.lang.IllegalStateException
import java.net.UnknownHostException

class JokesDataToDomainMapperTest{

    @Test
    fun success(){
        val joke = "Chuck Norris is the reason why Waldo is hiding."
        val expected =  JokesDomain.Success(listOf(joke))
        val jokesData = JokesData.JokesResult("success", listOf(JokesData.JokeCloud(1,joke)))
        val actual = jokesData.map(JokesDataToDomainMapper.Base())
        assertEquals(expected, actual)
    }

    @Test
    fun errorGeneric(){
        val error = IllegalStateException()
        val jokesData = JokesData.Fail(error)
        val expected = JokesDomain.Error(JokesDomain.ErrorType.GENERIC_ERROR)
        val actual = jokesData.map(JokesDataToDomainMapper.Base())
        assertEquals(expected, actual)
    }

    @Test
    fun errorHostException(){
        val error = UnknownHostException()
        val jokesData = JokesData.Fail(error)
        val expected = JokesDomain.Error(JokesDomain.ErrorType.NO_CONNECTION)
        val actual = jokesData.map(JokesDataToDomainMapper.Base())
        assertEquals(expected, actual)
    }

    @Test
    fun errorHtttp(){
        val response = Response.error<String>(400,MockResponseBody())
        val error = HttpException(response)
        val jokesData = JokesData.Fail(error)
        val expected = JokesDomain.Error(JokesDomain.ErrorType.SERVICE_UNAVAILABLE)
        val actual = jokesData.map(JokesDataToDomainMapper.Base())
        assertEquals(expected, actual)
    }

    private inner class MockResponseBody() : ResponseBody(){
        override fun contentLength(): Long = 400

        override fun contentType(): MediaType? = null

        override fun source(): BufferedSource {
            throw IllegalStateException("Not use in this test")
        }
    }
}