package com.qoiutestapp.chuckjokes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<C : Communication<T>, T : Any>
    (private val communication: Communication<T>) : ViewModel(), Communication<T> {

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        communication.observe(owner, observer)
    }

    override fun post(data: T) {
        communication.post(data)
    }
}