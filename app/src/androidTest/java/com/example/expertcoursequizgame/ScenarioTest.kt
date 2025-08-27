package com.example.expertcoursequizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expertcoursequizgame.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(
            question = "What color is the sky?",
            choices = listOf("blue", "green", "red", "yellow")
        )
    }

    /**
     * QGTC-01
     */

    @Test
    fun caseNumber1() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    /**
     * QGTC-02
     */

    @Test
    fun caseNumber2() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the grass?",
            choices = listOf("green", "blue", "yellow", "red")
        )
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

    }


    /**
     * QGTC-03
     */

    @Test
    fun caseNumber3() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the grass?",
            choices = listOf("green", "blue", "yellow", "red")
        )
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()
        gamePage.assertNotVisible()

        var gameOverPage = GameOverPage(incorrects = 2, corrects = 0)
        gameOverPage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()

        gamePage = GamePage(
            question = "What color is the blood?",
            choices = listOf("red", "blue", "yellow", "green")
        )

        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the sun?",
            choices = listOf("yellow", "blue", "green", "red")
        )


        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()

        gamePage.clickNext()

        gamePage.assertNotVisible()

        gameOverPage = GameOverPage(incorrects = 1, corrects = 1)
        gameOverPage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()


        gamePage = GamePage(
            question = "What color is the crocodile?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the christmas tree?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()

        gamePage.clickNext()
        gameOverPage = GameOverPage(incorrects = 0, corrects = 2)
        gameOverPage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()

    }

}