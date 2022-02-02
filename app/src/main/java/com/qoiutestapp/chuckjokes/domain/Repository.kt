package com.qoiutestapp.chuckjokes.domain

import com.qoiutestapp.chuckjokes.Abstract

interface Repository<E: Abstract.DataObject> {
    suspend fun fetchData(data: Int) : E
}