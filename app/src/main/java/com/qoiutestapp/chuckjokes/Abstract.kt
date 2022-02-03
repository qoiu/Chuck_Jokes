package com.qoiutestapp.chuckjokes

import java.lang.Exception

abstract class Abstract {
    interface DataObject

    interface Mapper {
        interface Data<I, O> : Mapper {
            fun map(data: I): O
        }

        interface DataToDomain<I, O> : Data<I, O> {
            fun map(e: Exception): O
        }
    }

}
