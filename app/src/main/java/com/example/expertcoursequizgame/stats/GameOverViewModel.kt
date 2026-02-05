package com.example.expertcoursequizgame.stats

import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.views.stats.StatsUiState

class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: StatsRepository
) : MyViewModel<Unit> {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }
    fun clear() {
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}