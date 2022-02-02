package com.qoiutestapp.chuckjokes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.qoiutestapp.chuckjokes.Abstract

abstract class BaseViewModel<C: Communication<T>,T: Any>
    (protected val communication: Communication<T>): ViewModel(), Abstract.Observe<T>{
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        communication.observe(owner, observer)
    }
}