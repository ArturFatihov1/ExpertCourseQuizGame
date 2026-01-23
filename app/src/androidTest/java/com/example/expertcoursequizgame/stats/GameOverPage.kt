package com.example.expertcoursequizgame.stats

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers
import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.game.ButtonUi
import com.example.expertcoursequizgame.stats.StatsUi
import org.hamcrest.Matcher

class GameOverPage(incorrects: Int, corrects: Int) {

    private val containerIdMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.withId(R.id.gameOverContainer))
    private val classTypeMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))
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