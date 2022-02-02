package com.qoiutestapp.chuckjokes.presentation

sealed class JokesUi {

    data class Success(
        val jokes: List<String>
    ) : JokesUi()

    object Fail : JokesUi()

    object Progress : JokesUi()
}