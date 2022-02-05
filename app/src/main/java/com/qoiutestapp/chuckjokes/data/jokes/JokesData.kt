package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.Abstract
import java.lang.Exception

sealed class JokesData : Abstract.DataObject {

    abstract fun <T> map(mapper: JokeResultMapper<T>): T

    data class JokesResult(
        private val type: String,
        private val value: List<JokeCloud>
    ) : JokesData() {
        override fun <T> map(mapper: JokeResultMapper<T>): T = mapper.map(type, value)

    }

    data class Fail(private val e: Exception) : JokesData() {
        override fun <T> map(mapper: JokeResultMapper<T>): T = mapper.map(e)
    }

    class JokeCloud(
        private val id: Int,
        private val joke: String
    ) : JokeCloudObject {
        override fun <T> map(mapper: JokeCloudMapper<T>): T = mapper.map(id, joke)
    }

    interface JokeCloudObject {
        fun <T> map(mapper: JokeCloudMapper<T>): T
    }

    interface JokeCloudMapper<T> : Abstract.Mapper {
        fun map(id: Int, joke: String): T
    }

    interface JokeResultMapper<T> : Abstract.Mapper {
        fun map(type: String, jokes: List<JokeCloud>): T
        fun map(e: Exception): T
    }
}