package com.qoiutestapp.chuckjokes

import android.app.Application
import com.qoiutestapp.chuckjokes.data.BaseJokesRepository
import com.qoiutestapp.chuckjokes.data.jokes.JokesData
import com.qoiutestapp.chuckjokes.data.jokes.JokesDataToDomainMapper
import com.qoiutestapp.chuckjokes.data.jokes.JokesService
import com.qoiutestapp.chuckjokes.domain.JokeDomainMapper
import com.qoiutestapp.chuckjokes.domain.JokesDomain
import com.qoiutestapp.chuckjokes.domain.JokesInteractor
import com.qoiutestapp.chuckjokes.domain.Repository
import com.qoiutestapp.chuckjokes.presentation.ui.jokes.CommunicationJokes
import com.qoiutestapp.chuckjokes.presentation.ui.jokes.JokesDomainToUiMapper
import com.qoiutestapp.chuckjokes.presentation.ui.jokes.JokesUi
import com.qoiutestapp.chuckjokes.presentation.ui.jokes.JokesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val jokesModule = module {
            single<Abstract.StringProvider> { StringResources(this@JokesApp) }
            single<Repository<JokesData>> { BaseJokesRepository(configRetrofit()) }
            single<JokesDataToDomainMapper<JokesDomain>> { JokesDataToDomainMapper.Base() }
            single<JokeDomainMapper<JokesUi>> { JokesDomainToUiMapper(get()) }
            single<JokesInteractor> { JokesInteractor.Base(get(), get()) }
            viewModel { JokesViewModel(get(), get(), get(), CommunicationJokes()) }
        }

        startKoin {
            androidContext(this@JokesApp)
            modules(jokesModule)
        }
    }

    private fun configRetrofit(): JokesService {
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

        return retrofit.create(JokesService::class.java)
    }
}