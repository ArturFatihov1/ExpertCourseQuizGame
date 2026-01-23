package com.example.expertcoursequizgame.stats

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.expertcoursequizgame.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class StatsUi(
    incorrects: Int,
    corrects: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction =
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.statsTextView),
                ViewMatchers.isAssignableFrom(TextView::class.java),
                ViewMatchers.withText("Game Over\n\nCorrects: $corrects\nIncorrects: $incorrects"),
                containerIdMatcher,
                classTypeMatcher
            )
        )

    fun assertVisible() {
        interaction.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(ViewAssertions.doesNotExist())
    }

}