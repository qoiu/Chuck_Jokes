package com.qoiutestapp.chuckjokes

import java.lang.Exception
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.qoiutestapp.chuckjokes.domain.JokesDomain

abstract class Abstract {

    interface DataObject

    interface Mapper {
        interface Data<I, O> : Mapper {
            fun map(data: I): O
        }

        interface DataToDomain<I, O> : Data<I, O> {
            fun map(e: Exception): O
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: JokesDomain.ErrorType): T
        }
    }

    interface StringProvider {
        fun string(id: Int): String
    }

    interface FetchAmount {
        fun fetch(count: String)
    }

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }
}