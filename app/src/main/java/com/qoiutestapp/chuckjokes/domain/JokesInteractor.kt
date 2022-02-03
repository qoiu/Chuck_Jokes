package com.qoiutestapp.chuckjokes.domain

import com.qoiutestapp.chuckjokes.data.jokes.JokesData
import com.qoiutestapp.chuckjokes.data.jokes.JokesDataToDomainMapper


interface JokesInteractor {
    suspend fun fetchJokes(amount: Int): JokesDomain
    class Base(
        private val repository: Repository<JokesData>,
        private val mapper: JokesDataToDomainMapper<JokesDomain>
    ) : JokesInteractor {
        override suspend fun fetchJokes(amount: Int): JokesDomain =
            repository.fetchData(amount).map(mapper)
    }
}