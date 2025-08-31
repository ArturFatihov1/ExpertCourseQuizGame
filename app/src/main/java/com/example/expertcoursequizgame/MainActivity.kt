package com.example.expertcoursequizgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.expertcoursequizgame.game.GameScreen
import com.example.expertcoursequizgame.game.NavigateToGame
import com.example.expertcoursequizgame.stats.GameOverScreen
import com.example.expertcoursequizgame.stats.NavigateToGameOver

class MainActivity : AppCompatActivity(), Navigate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)

}

interface Navigate : NavigateToGame, NavigateToGameOver {
    fun navigate(screen: Screen)

    override fun navigateToGameOver() = navigate(GameOverScreen)

    override fun navigateToGame() = navigate(GameScreen)
}