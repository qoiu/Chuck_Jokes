package com.qoiutestapp.chuckjokes.domain

sealed class JokesDomain {
    abstract fun <T> map(mapper: JokeDomainMapper<T>): T

    data class Success(
        private val data: List<String>
    ) : JokesDomain() {
        override fun <T> map(mapper: JokeDomainMapper<T>): T = mapper.map(data)
    }

    data class Error(
        private val e: ErrorType
    ) : JokesDomain() {
        override fun <T> map(mapper: JokeDomainMapper<T>): T = mapper.map(e)
    }

    enum class ErrorType {
        NO_CONNECTION,
        SERVICE_UNAVAILABLE,
        GENERIC_ERROR
    }
}