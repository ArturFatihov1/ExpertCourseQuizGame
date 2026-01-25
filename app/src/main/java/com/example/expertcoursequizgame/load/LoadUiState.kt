package com.example.expertcoursequizgame.load

import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.game.NavigateToGame
import com.example.expertcoursequizgame.views.error.ErrorUiState
import com.example.expertcoursequizgame.views.error.UpdateError
import com.example.expertcoursequizgame.views.visiblebutton.UpdateVisibility
import com.example.expertcoursequizgame.views.visiblebutton.VisibilityUiState

interface LoadUiState {
    fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(game: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityUiState,
        private val progressUiState: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryUiState)
            progressBar.update(progressUiState)
        }
    }

    object Progress : Abstract(
        ErrorUiState.Hide,
        VisibilityUiState.Gone,
        VisibilityUiState.Visible
    )

    object Success : Abstract(
        ErrorUiState.Hide,
        VisibilityUiState.Gone,
        VisibilityUiState.Gone
    ) {
        override fun navigate(game: NavigateToGame) = game.navigateToGame()
    }

    data class Error(private val message: String) : Abstract(
        ErrorUiState.Show(R.string.no_internet_connection),
        VisibilityUiState.Visible,
        VisibilityUiState.Gone
    )
}