package com.example.expertcoursequizgame.stats

import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.game.FakeClearViewModel
import com.example.expertcoursequizgame.views.stats.StatsUiState
import org.junit.Test
import kotlin.test.assertEquals


class GameOverViewModelTest {
    private lateinit var clearViewModel: ClearViewModel
    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = GameOverViewModel(FakeClearViewModel(), repository = repository)
        assertEquals(StatsUiState.Base(2, 3), viewModel.init(isFirstRun = true))
        assertEquals(1, repository.clearCalledCount)

        assertEquals(StatsUiState.Empty, viewModel.init(isFirstRun = false))
        assertEquals(1, repository.clearCalledCount)
    }
}

private class FakeRepository : StatsRepository {
    override fun stats(): Pair<Int, Int> = Pair(2, 3)
    var clearCalledCount = 0

    override fun clear() {
        clearCalledCount++
    }
}