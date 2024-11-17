package com.example.expertcoursequizgame.game

import android.view.TextureView
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcoursequizgame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class QuestionUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.questionTextView),
            withText(text),
            isAssignableFrom(TextureView::class.java)
        )
    )

    fun assertTextVisible() {
        interaction.check(matches(isDisplayed()))
    }

}
