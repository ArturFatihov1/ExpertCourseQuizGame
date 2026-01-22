package com.example.expertcoursequizgame.game

import com.example.expertcoursequizgame.stats.NavigateToGameOver
import com.example.expertcoursequizgame.views.choice.ChoiceUiState
import com.example.expertcoursequizgame.views.choice.UpdateChoiceButton
import com.example.expertcoursequizgame.views.question.UpdateText
import com.example.expertcoursequizgame.views.visiblebutton.UpdateVisibility
import com.example.expertcoursequizgame.views.visiblebutton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        forthChoiceButton: UpdateChoiceButton,
        nextButton: UpdateVisibility,
        checkButton: UpdateVisibility
    ) = Unit

    fun navigate(navigate: NavigateToGameOver) = Unit

    object Empty : GameUiState
    object Finish : GameUiState {
        override fun navigate(navigate: NavigateToGameOver) = navigate.navigateToGameOver()
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            questionTextView.update(question)
            firstChoiceButton.update(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.update(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.update(ChoiceUiState.Initial(choices[2]))
            forthChoiceButton.update(ChoiceUiState.Initial(choices[3]))
            nextButton.update(VisibilityUiState.Gone)
            checkButton.update(VisibilityUiState.Gone)
        }

    }

    data class ChoiceMade(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            forthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Visible)
        }

    }

    data class AnswerChecked(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            forthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Gone)
            nextButton.update(VisibilityUiState.Visible)
        }
    }
}