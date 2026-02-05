package com.example.expertcoursequizgame.core

import android.app.Application
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.di.Core
import com.example.expertcoursequizgame.di.ManageViewModels
import com.example.expertcoursequizgame.di.ProvideViewModel

class QuizApp : Application(), ProvideViewModel {


    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel<*>>) =
                factory.clear(viewModelClass)
        }
        val make = ProvideViewModel.Make(Core(this, clearViewModel))
        factory = ManageViewModels.Factory(make)
    }

    override fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T {
        return factory.makeViewModel(clasz)
    }
}