package com.qoiutestapp.chuckjokes.domain

sealed class JokesDomain {
    data class Success(
        private val data: List<String>
    ): JokesDomain()

    data class Error(
        private val e: ErrorType
    ): JokesDomain()

    enum class ErrorType{
        NO_CONNECTION,
        SERVICE_UNAVAILABLE,
        GENERIC_ERROR
    }
}