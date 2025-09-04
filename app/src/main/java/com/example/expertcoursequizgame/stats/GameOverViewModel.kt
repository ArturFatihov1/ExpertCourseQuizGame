package com.example.expertcoursequizgame.stats

import com.example.expertcoursequizgame.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {
    fun statsUiState(): StatsUiState {
        val (corrects, incorrects) = repository.stats()
        return StatsUiState.Base(corrects, incorrects)
    }

    fun clear() {
        repository.clear()
    }
}