package com.qoiutestapp.chuckjokes.data

import com.qoiutestapp.chuckjokes.data.jokes.JokesResult
import com.qoiutestapp.chuckjokes.data.jokes.JokesService
import com.qoiutestapp.chuckjokes.domain.JokesRepository

class BaseJokesRepository(private val service: JokesService) : JokesRepository {
    override suspend fun fetchData(data: Int): JokesResult {
        return service.fetchJokes(data)
    }
}