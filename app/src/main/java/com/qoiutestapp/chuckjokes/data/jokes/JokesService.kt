package com.qoiutestapp.chuckjokes.data.jokes

import retrofit2.http.GET
import retrofit2.http.Path

interface JokesService {

    @GET("random/{amount}")
    suspend fun fetchJokes(@Path("amount")amount: Int): JokesResult
}