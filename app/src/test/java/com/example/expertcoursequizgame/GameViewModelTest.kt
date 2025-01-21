package com.example.expertcoursequizgame

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    /**
    QGTC-01
     */

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choice = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choice = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseThird()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseForth()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            question = "q1",
            choice = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.InCorrect(text = "c4"),
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.AssertQuestion(
            question = "q2",
            choice = listOf<String>("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected, actual)

    }
}

private class FakeRepository : GameRepository {
    //todo
}