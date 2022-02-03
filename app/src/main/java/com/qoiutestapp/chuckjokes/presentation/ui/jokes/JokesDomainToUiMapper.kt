package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import com.qoiutestapp.chuckjokes.Abstract
import com.qoiutestapp.chuckjokes.R
import com.qoiutestapp.chuckjokes.domain.JokeDomainMapper
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesDomain.ErrorType.*

class JokesDomainToUiMapper(private val stringProvider: Abstract.StringProvider) :
    JokeDomainMapper<JokesUi> {
    override fun map(data: List<String>) = JokesUi.Success(data)

    override fun map(errorType: JokesDomain.ErrorType) = JokesUi.Error(
        stringProvider.string(
            when (errorType) {
                NO_CONNECTION -> R.string.error_connection
                SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
                GENERIC_ERROR -> R.string.error_generic
            }
        )
    )
}