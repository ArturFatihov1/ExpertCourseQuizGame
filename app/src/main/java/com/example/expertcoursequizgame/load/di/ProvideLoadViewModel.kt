package com.example.expertcoursequizgame.load.di

import com.example.expertcoursequizgame.RunAsync
import com.example.expertcoursequizgame.di.AbstractProvideViewModel
import com.example.expertcoursequizgame.di.Core
import com.example.expertcoursequizgame.di.Module
import com.example.expertcoursequizgame.di.ProvideViewModel
import com.example.expertcoursequizgame.load.data.LoadRepository
import com.example.expertcoursequizgame.load.data.ParseQuestionAndChoices
import com.example.expertcoursequizgame.load.data.QuizResponse
import com.example.expertcoursequizgame.load.data.QuizService
import com.example.expertcoursequizgame.load.data.StringCache
import com.example.expertcoursequizgame.load.presentation.LoadViewModel
import com.example.expertcoursequizgame.load.presentation.UiObservable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {
    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {
        val responseDefault = QuizResponse(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.HEADERS)
        }).readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create(core.gson))
            .build()

        return LoadViewModel(
            LoadRepository.Base(
                retrofit.create(QuizService::class.java),
                ParseQuestionAndChoices.Base(core.gson),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            UiObservable.Base(),
            RunAsync.Base()
        )
    }
}
