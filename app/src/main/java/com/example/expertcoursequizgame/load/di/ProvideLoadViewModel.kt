package com.example.expertcoursequizgame.load.di

import com.example.expertcoursequizgame.core.IntCache
import com.example.expertcoursequizgame.di.AbstractProvideViewModel
import com.example.expertcoursequizgame.di.Core
import com.example.expertcoursequizgame.di.Module
import com.example.expertcoursequizgame.di.ProvideViewModel
import com.example.expertcoursequizgame.load.data.LoadRepository
import com.example.expertcoursequizgame.load.data.cloud.CloudDataSource
import com.example.expertcoursequizgame.load.data.cloud.QuizService
import com.example.expertcoursequizgame.load.presentation.LoadUiObservable
import com.example.expertcoursequizgame.load.presentation.LoadViewModel
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
        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return LoadViewModel(
            if (core.runUiTests)
                LoadRepository.Fake()
            else
                LoadRepository.Base(
                    IntCache.Base(core.sharedPreferences, "indexKey", core.size),
                    CloudDataSource.Base(
                        retrofit.create(QuizService::class.java),
                        core.size,
                    ),
                    core.cacheModule.dao()
                ),
            LoadUiObservable.Base(),
            core.runAsync,
            core.clearViewModel
        )
    }
}
