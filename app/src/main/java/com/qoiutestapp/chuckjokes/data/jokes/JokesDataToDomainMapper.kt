package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesDomain.ErrorType.*
import retrofit2.HttpException
import java.net.UnknownHostException

class JokesDataToDomainMapper : JokesData.JokeResultMapper<JokesDomain> {

    override fun map(type: String, jokes: List<JokesData.JokeCloud>): JokesDomain =
        JokesDomain.Success(jokes.map{it.map(JokesDataCloudToStringMapper())})

    override fun map(e: Exception) = JokesDomain.Error(
        when (e) {
            is UnknownHostException -> NO_CONNECTION
            is HttpException -> SERVICE_UNAVAILABLE
            else -> GENERIC_ERROR
        }
    )
}