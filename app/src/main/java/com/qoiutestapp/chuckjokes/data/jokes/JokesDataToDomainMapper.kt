package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.Abstract
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesDomain.ErrorType.*
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface JokesDataToDomainMapper<T> : Abstract.Mapper.DataToDomain<JokesData.JokesResult, T> {
    class Base : JokesDataToDomainMapper<JokesDomain> {

        override fun map(data: JokesData.JokesResult): JokesDomain =
            JokesDomain.Success(data.value.map { it.joke })

        override fun map(e: Exception) = JokesDomain.Error(
            when (e) {
                is UnknownHostException -> NO_CONNECTION
                is HttpException -> SERVICE_UNAVAILABLE
                else -> GENERIC_ERROR
            }
        )
    }
}