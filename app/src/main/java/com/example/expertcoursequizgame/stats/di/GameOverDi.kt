package com.example.expertcoursequizgame.stats.di

import com.example.expertcoursequizgame.core.IntCache
import com.example.expertcoursequizgame.di.AbstractProvideViewModel
import com.example.expertcoursequizgame.di.Core
import com.example.expertcoursequizgame.di.Module
import com.example.expertcoursequizgame.di.ProvideViewModel
import com.example.expertcoursequizgame.stats.GameOverViewModel
import com.example.expertcoursequizgame.stats.StatsRepository


class GameOverModule(private val core: Core) : Module<GameOverViewModel> {

    override fun viewModel(): GameOverViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)

        return GameOverViewModel(
            core.clearViewModel,
            StatsRepository.Base(
                corrects,
                incorrects
            )
        )
    }

}

class ProvideGameOverViewModel(core: Core, next: ProvideViewModel) : AbstractProvideViewModel(
    core, next,
    GameOverViewModel::class.java
) {
    override fun module(): Module<*> = GameOverModule(core)
}