package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.Abstract
import java.lang.Exception

sealed class JokesData : Abstract.DataObject {

    abstract fun <T> map(mapper: JokesDataToDomainMapper<T>): T

    data class JokesResult(
        val type: String,
        val value: List<JokeCloud>
    ) : JokesData() {
        override fun <T> map(mapper: JokesDataToDomainMapper<T>) = mapper.map(this)
    }

    data class JokeCloud(
        val id: Int,
        val joke: String
    )

    data class Fail(val e: Exception) : JokesData() {
        override fun <T> map(mapper: JokesDataToDomainMapper<T>) = mapper.map(e)
    }
}