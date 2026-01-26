package com.example.expertcoursequizgame.load.di

import com.example.expertcoursequizgame.di.AbstractProvideViewModel
import com.example.expertcoursequizgame.di.Core
import com.example.expertcoursequizgame.di.Module
import com.example.expertcoursequizgame.di.ProvideViewModel
import com.example.expertcoursequizgame.load.data.LoadRepository
import com.example.expertcoursequizgame.load.data.ParseQuestionAndChoices
import com.example.expertcoursequizgame.load.data.Response
import com.example.expertcoursequizgame.load.data.StringCache
import com.example.expertcoursequizgame.load.presentation.LoadViewModel
import com.example.expertcoursequizgame.load.presentation.UiObservable

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {
    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {
        val responseDefault = Response(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        return LoadViewModel(
            LoadRepository.Base(
                ParseQuestionAndChoices.Base(core.gson),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            UiObservable.Base()
        )
    }
}
