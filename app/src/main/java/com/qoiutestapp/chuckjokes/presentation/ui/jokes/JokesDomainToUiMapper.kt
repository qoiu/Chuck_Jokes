package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.os.Build
import android.text.Html
import com.qoiutestapp.chuckjokes.Abstract
import com.qoiutestapp.chuckjokes.R
import com.qoiutestapp.chuckjokes.domain.JokeDomainMapper
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesDomain.ErrorType.*

class JokesDomainToUiMapper(private val stringProvider: Abstract.StringProvider) :
    JokeDomainMapper<JokesUi> {
    override fun map(data: List<String>) = JokesUi.Success(
        data.map {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(it).toString()
            }
        })

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