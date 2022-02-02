package com.qoiutestapp.chuckjokes

import android.app.Application
import com.qoiutestapp.chuckjokes.data.jokes.JokesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        configRetrofit()
    }

    private fun configRetrofit(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.icndb.com/jokes/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jokesService = retrofit.create(JokesService::class.java)
    }

    companion object{
        var jokesService: JokesService? = null
    }
}