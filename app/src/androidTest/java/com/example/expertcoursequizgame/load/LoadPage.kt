package com.example.expertcoursequizgame.load

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.game.ButtonUi
import org.hamcrest.Matcher

class LoadPage {

    private val containerIdMatcher: Matcher<View> =
        ViewMatchers.withParent(withId(R.id.loadContainer))
    private val classTypeMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))

    private val progressUi =
        ProgressUi(
            containerIdMatcher = containerIdMatcher,
            classTypeMatcher = classTypeMatcher
        )
    private val errorUi =
        ErrorUi(
            containerIdMatcher = containerIdMatcher,
            classTypeMatcher = classTypeMatcher
        )
    private val retryUi = ButtonUi(
        R.id.retryButton,
        R.string.retry,
        "#A020F0",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertProgressState() {
        errorUi.assertNotVisible()
        progressUi.assertVisible()
        retryUi.assertNotVisible()
    }

    fun waitTillError() {
        errorUi.waitTillVisible()
    }

    fun assertErrorState() {
        errorUi.assertVisible()
        progressUi.assertNotVisible()
        retryUi.assertVisible()
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitTillGone() {
        errorUi.waitTillDoesntExist()
    }
}