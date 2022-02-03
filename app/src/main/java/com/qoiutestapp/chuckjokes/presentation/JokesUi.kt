package com.qoiutestapp.chuckjokes.presentation

import android.view.View

sealed class JokesUi {
    open fun progressVisibility() = View.GONE

    data class Success(
        val jokes: List<String>
    ) : JokesUi()

    class Error(val e: String) : JokesUi()

    object Progress : JokesUi(){
        override fun progressVisibility() = View.VISIBLE
    }
}