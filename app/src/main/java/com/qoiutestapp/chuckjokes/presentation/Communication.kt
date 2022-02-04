package com.qoiutestapp.chuckjokes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.qoiutestapp.chuckjokes.Abstract
import java.lang.IllegalStateException

interface Communication<T : Any> : Abstract.Observe<T> {

    fun post(data: T)

    abstract class Base<T : Any> : Communication<T> {
        private val liveData = MutableLiveData<T>()
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }

        override fun post(data: T) {
            liveData.postValue(data)
        }
    }

    class Test<T : Any>(private val action: (T) -> Unit) : Communication<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            throw IllegalStateException("Not supported in test")
        }

        override fun post(data: T) {
            action(data)
        }
    }
}