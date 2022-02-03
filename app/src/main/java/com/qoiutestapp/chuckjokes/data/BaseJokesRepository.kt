package com.qoiutestapp.chuckjokes.data

import com.qoiutestapp.chuckjokes.data.jokes.JokesData
import com.qoiutestapp.chuckjokes.data.jokes.JokesService
import com.qoiutestapp.chuckjokes.domain.Repository

class BaseJokesRepository(private val service: JokesService) : Repository<JokesData> {
    override suspend fun fetchData(data: Int): JokesData {
        return try {
            service.fetchRandomJokes(data)
        } catch (e: Exception) {
            JokesData.Fail(e)
        }
    }
}