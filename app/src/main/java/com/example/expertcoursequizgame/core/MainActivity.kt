package com.example.expertcoursequizgame.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.di.ProvideViewModel
import com.example.expertcoursequizgame.game.GameScreen
import com.example.expertcoursequizgame.game.NavigateToGame
import com.example.expertcoursequizgame.load.presentation.LoadScreen
import com.example.expertcoursequizgame.load.presentation.NavigateToLoad
import com.example.expertcoursequizgame.stats.GameOverScreen
import com.example.expertcoursequizgame.stats.NavigateToGameOver

class MainActivity : AppCompatActivity(), Navigate, ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) //todo mainViewModel later
            navigateToLoad()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)

    override fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T =
        (application as ProvideViewModel).makeViewModel(clasz)
}

interface Navigate : NavigateToGame, NavigateToGameOver, NavigateToLoad {
    fun navigate(screen: Screen)

    override fun navigateToGameOver() = navigate(GameOverScreen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToLoad() = navigate(LoadScreen)

}