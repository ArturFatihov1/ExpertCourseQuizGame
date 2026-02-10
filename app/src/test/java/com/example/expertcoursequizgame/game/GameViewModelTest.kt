package com.example.expertcoursequizgame.game

import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.load.FakeRunAsync
import com.example.expertcoursequizgame.load.FakeUiObservable
import com.example.expertcoursequizgame.views.choice.ChoiceUiState
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GameViewModelTest {
    private lateinit var runAsync: FakeRunAsync
    private lateinit var observable: FakeGameUiObservable
    private lateinit var viewModel: GameViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setup() {
        runAsync = FakeRunAsync()
        observable = FakeGameUiObservable.Base()
        repository = FakeRepository()
        viewModel = GameViewModel(
            observable,
            FakeClearViewModel(),
            repository = repository,
            runAsync
        )
    }

    /**
    QGTC-01
     */

    @Test
    fun caseNumber1() {
        viewModel.init()
        runAsync.returnResult()
        var actual: GameUiState = observable.postUiStateCalledList.last()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        viewModel.chooseFirst()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.check()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.next()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected, actual)
        assertEquals(false, repository.clearCalled)

        viewModel.chooseFirst()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.check()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.next()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
        assertEquals(true, repository.clearCalled)
    }

    @Test
    fun caseNumber2() {
        viewModel.init()
        runAsync.returnResult()
        var actual: GameUiState = observable.postUiStateCalledList.last()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        viewModel.chooseFirst()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.chooseSecond()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.chooseThird()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.chooseForth()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        viewModel.check()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.InCorrect,
            )
        )
        assertEquals(expected, actual)

        viewModel.next()
        runAsync.returnResult()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf<String>("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected, actual)

    }
}

private class FakeRepository : GameRepository {

    private val list: List<QuestionAndChoices> = listOf(
        QuestionAndChoices(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4"),
            correctIndex = 0
        ),
        QuestionAndChoices(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4"),
            correctIndex = 0
        )
    )

    private var index = 0

    override suspend fun questionAndChoices(): QuestionAndChoices {
        return list[index]
    }

    private var userChoiceIndex = -1

    override fun saveUserChoice(index: Int) {
        userChoiceIndex = index
    }

    override suspend fun check(): CorrectAndUserChoiceIndexes {
        return CorrectAndUserChoiceIndexes(
            correctIndex = questionAndChoices().correctIndex,
            userChoiceIndex = userChoiceIndex
        )
    }

    override fun next() {
        userChoiceIndex = -1
        index++

    }

    override fun isLastQuestion(): Boolean {
        return index == list.size
    }

    var clearCalled = false

    override suspend fun clear() {
        clearCalled = true
    }
}

class FakeClearViewModel : ClearViewModel {
    private var actual: Class<out MyViewModel<*>>? = null
    override fun clear(viewModelClass: Class<out MyViewModel<*>>) {
        actual = viewModelClass
    }

    fun assertClearCalled(expected: Class<out MyViewModel<*>>) {
        assertEquals(expected, actual)
    }

}


private interface FakeGameUiObservable : FakeUiObservable<GameUiState>, GameUiObservable {
    class Base : FakeUiObservable.Abstract<GameUiState>(), FakeGameUiObservable
}