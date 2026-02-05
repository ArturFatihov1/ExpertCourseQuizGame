package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.core.IntCache
import com.example.expertcoursequizgame.core.MainViewModel

class MainModule(
    private val core: Core
) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        IntCache.Base(core.sharedPreferences, "indexKey", core.size),
        core.size
    )

}

class ProvideMainViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, MainViewModel::class.java) {

    override fun module(): Module<*> = MainModule(core)
}