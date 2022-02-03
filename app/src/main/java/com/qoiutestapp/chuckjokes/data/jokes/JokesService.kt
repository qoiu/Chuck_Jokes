package com.qoiutestapp.chuckjokes.data.jokes

import retrofit2.http.GET
import retrofit2.http.Path

interface JokesService {

    @GET("random/{amount}")
    suspend fun fetchRandomJokes(@Path("amount")amount: Int): JokesData.JokesResult
}