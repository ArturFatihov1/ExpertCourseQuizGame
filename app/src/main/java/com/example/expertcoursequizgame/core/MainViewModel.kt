package com.example.expertcoursequizgame.core

import com.example.expertcoursequizgame.game.GameScreen
import com.example.expertcoursequizgame.load.presentation.LoadScreen

class MainViewModel(
    private val index: IntCache,
    private val size: Int
) : MyViewModel<Unit> {

    fun firstScreen(firstRun: Boolean): Screen {
        return if (firstRun) {
            if (index.read() == size) {
                LoadScreen
            } else
                GameScreen
        } else
            Screen.Empty
    }
}