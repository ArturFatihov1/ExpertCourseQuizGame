package com.example.expertcoursequizgame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class GamePage(
    question: String,
    choices: List<String>
) {
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val questionUi =
        QuestionUi(
            text = question,
            containerIdMatcher = containerIdMatcher,
            containerclassTypeMatcher = classTypeMatcher
        )
    private val choicesUiList = choices.map {
        ChoiceUi(
            text = it,
            containerIdMatcher = containerIdMatcher,
            containerclassTypeMatcher = classTypeMatcher
        )
    }

    private val checkUi = ButtonUi(
        textResId = R.string.check,
        colorHex = "#6C106C",
        containerIdMatcher = containerIdMatcher,
        containerclassTypeMatcher = classTypeMatcher
    )
    private val nextUi = ButtonUi(
        textResId = R.string.next,
        colorHex = "#8897D4",
        containerIdMatcher = containerIdMatcher,
        containerclassTypeMatcher = classTypeMatcher
    )

    fun assertAskedQuestionState() {
        questionUi.assertTextVisiable()
        choicesUiList.forEach {
            it.assertAvailableToChooseState()
        }
        checkUi.assertNotVisiable()
        nextUi.assertNotVisiable()
    }

    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMadeState() {
        questionUi.assertTextVisiable()
        choicesUiList.first().assertNotAvailableToChooseState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertAvailableToChooseState()
        }
        checkUi.assertVisiable()
        nextUi.assertNotVisiable()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertAnswerCheckedStateFirstIsCorrect() {
        questionUi.assertTextVisiable()
        choicesUiList.first().assertCorrectState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvailableToChooseState()
        }
        checkUi.assertNotVisiable()
        nextUi.assertVisiable()
    }

    fun clickSecondChoice() {
        choicesUiList[1].click()
    }

    fun assertSecondChoiceMadeState() {
        questionUi.assertTextVisiable()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 1)
                choiceUi.assertNotAvailableToChooseState()
            else
                choiceUi.assertAvailableToChooseState()
        }
        checkUi.assertVisiable()
        nextUi.assertNotVisiable()
    }

    fun assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect() {
        questionUi.assertTextVisiable()
        choicesUiList.first().assertCorrectState()
        choicesUiList[1].assertIncorrectState()
        choicesUiList[2].assertNotAvailableToChoose()
        choicesUiList[3].assertNotAvailableToChoose()
        checkUi.assertNotVisiable()
        nextUi.assertVisiable()
    }

    fun clickNext() {
        nextUi.click()
    }
}