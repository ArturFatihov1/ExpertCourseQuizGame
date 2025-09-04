package com.example.expertcoursequizgame

import android.app.Application
import android.content.Context
import com.example.expertcoursequizgame.game.GameRepository
import com.example.expertcoursequizgame.game.GameViewModel
import com.example.expertcoursequizgame.stats.GameOverViewModel
import com.example.expertcoursequizgame.stats.StatsRepository

class QuizApp : Application() {

    lateinit var gameViewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
        val corrects = IntCache.Base(sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(sharedPreferences, "incorrects", 0)

        gameViewModel = GameViewModel(
            GameRepository.Base(
                corrects,
                incorrects,
                IntCache.Base(sharedPreferences, "indexKey", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1)
            )
        )
        gameOverViewModel = GameOverViewModel(
            StatsRepository.Base(
                corrects,
                incorrects
            )
        )
    }
}
