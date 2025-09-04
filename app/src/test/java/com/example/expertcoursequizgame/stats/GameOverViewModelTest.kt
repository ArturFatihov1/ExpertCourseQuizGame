package com.example.expertcoursequizgame.stats

import com.example.expertcoursequizgame.views.stats.StatsUiState
import org.junit.Test
import kotlin.test.assertEquals


class GameOverViewModelTest {

    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = GameOverViewModel(repository = repository)
        assertEquals(StatsUiState.Base(2, 3), viewModel.statsUiState())
        assertEquals(false, repository.clearCalled)

        viewModel.clear()
        assertEquals(true, repository.clearCalled)
    }
}

private class FakeRepository : StatsRepository {
    override fun stats(): Pair<Int, Int> = Pair(2, 3)
    var clearCalled = false
    override fun clear() {
        clearCalled = true
    }
}