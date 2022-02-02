package com.qoiutestapp.chuckjokes

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

abstract class Abstract {
    interface DataObject

    interface Observe<T>{
        fun observe(owner: LifecycleOwner,observer: Observer<T>)
    }
}