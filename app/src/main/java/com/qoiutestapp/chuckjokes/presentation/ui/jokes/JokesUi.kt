package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.view.View

sealed class JokesUi {
    open fun progressVisibility() = View.GONE

    data class Success(
        val jokes: List<String>
    ) : JokesUi()

    data class Error(val e: String) : JokesUi()

    object Progress : JokesUi() {
        override fun progressVisibility() = View.VISIBLE
    }
}