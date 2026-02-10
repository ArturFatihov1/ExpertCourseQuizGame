package com.example.expertcoursequizgame.game

import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.core.RunAsync
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.views.choice.ChoiceUiState

class GameViewModel(
    uiObservable: GameUiObservable,
    private val clearViewModel: ClearViewModel,
    private val repository: GameRepository,
    private val runAsync: RunAsync
) : MyViewModel.Abstract<GameUiState>(uiObservable) {

    private val uiUpdate: (GameUiState) -> Unit = {
        uiObservable.postUiState(it)
    }

    fun chooseFirst() {
        runAsync.handleAsync(viewModelScope, {
            repository.saveUserChoice(0)
            val data = repository.questionAndChoices()
            GameUiState.ChoiceMade(
                data.choices.mapIndexed { index, _ ->
                    if (index == 0)
                        ChoiceUiState.NotAvailableToChoose
                    else
                        ChoiceUiState.AvailableToChoose
                }
            )
        }, uiUpdate)
    }

    fun chooseSecond() {

        runAsync.handleAsync(viewModelScope, {
            repository.saveUserChoice(1)
            val data = repository.questionAndChoices()
            GameUiState.ChoiceMade(
                data.choices.mapIndexed { index, _ ->
                    if (index == 1)
                        ChoiceUiState.NotAvailableToChoose
                    else
                        ChoiceUiState.AvailableToChoose
                }
            )
        }, uiUpdate)
    }

    fun chooseThird() {
        runAsync.handleAsync(viewModelScope, {
            repository.saveUserChoice(2)
            val data = repository.questionAndChoices()
            GameUiState.ChoiceMade(
                data.choices.mapIndexed { index, _ ->
                    if (index == 2)
                        ChoiceUiState.NotAvailableToChoose
                    else
                        ChoiceUiState.AvailableToChoose
                }
            )
        }, uiUpdate)
    }

    fun chooseForth() {
        runAsync.handleAsync(viewModelScope, {
            repository.saveUserChoice(3)
            val data = repository.questionAndChoices()
            GameUiState.ChoiceMade(
                data.choices.mapIndexed { index, _ ->
                    if (index == 3)
                        ChoiceUiState.NotAvailableToChoose
                    else
                        ChoiceUiState.AvailableToChoose
                }
            )
        }, uiUpdate)
    }

    fun check() {
        runAsync.handleAsync(viewModelScope, {
            val data = repository.questionAndChoices()
            val correctAndUserChoiceIndexes = repository.check()
            GameUiState.AnswerChecked(
                data.choices.mapIndexed { index, choice ->
                    if (correctAndUserChoiceIndexes.correctIndex == index)
                        ChoiceUiState.Correct
                    else if (correctAndUserChoiceIndexes.userChoiceIndex == index) {
                        ChoiceUiState.InCorrect
                    } else {
                        ChoiceUiState.NotAvailableToChoose
                    }
                }
            )
        }, uiUpdate)
    }

    fun next() {
        repository.next()
        return if (repository.isLastQuestion()) {
            runAsync.handleAsync(viewModelScope, {
                repository.clear()
                clearViewModel.clear(GameViewModel::class.java)
                GameUiState.Finish
            }, uiUpdate)
        } else
            init()

    }

    fun init(firstRun: Boolean = true) {
        if (firstRun) {
            runAsync.handleAsync(viewModelScope, {
                val data = repository.questionAndChoices()
                GameUiState.AskedQuestion(
                    data.question,
                    data.choices
                )
            }, uiUpdate)
        }
    }
}