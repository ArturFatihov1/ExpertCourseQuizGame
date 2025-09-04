package com.example.expertcoursequizgame.stats

import com.example.expertcoursequizgame.views.stats.StatsUiState

class GameOverViewModel {
    fun statsUiState(): StatsUiState {
        return StatsUiState.Base(1, 1)
    }
}