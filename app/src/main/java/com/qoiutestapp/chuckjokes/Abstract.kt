package com.qoiutestapp.chuckjokes

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

abstract class Abstract {
    interface StringProvider {
        fun string(id: Int): String
    }

    interface FetchAmount {
        fun fetch(count: String)
    }

    interface DataObject

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }
}