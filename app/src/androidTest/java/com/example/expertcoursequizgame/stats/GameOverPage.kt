package com.example.expertcoursequizgame.stats

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers
import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.game.ButtonUi
import org.hamcrest.Matcher

class GameOverPage(incorrects: Int, corrects: Int) {

    private val containerIdMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.withId(R.id.gameOverContainer))
    private val classTypeMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(FrameLayout::class.java))
    private val statsUi =
        StatsUi(incorrects = incorrects, corrects = corrects, containerIdMatcher, classTypeMatcher)
    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        R.string.new_game,
        "#462CE1",
        containerIdMatcher,
        classTypeMatcher
    )


    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDoesNotExist()
    }

}