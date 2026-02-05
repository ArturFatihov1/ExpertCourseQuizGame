package com.example.expertcoursequizgame.game

import com.example.expertcoursequizgame.load.presentation.UiObservable

interface GameUiObservable : UiObservable<GameUiState> {

    class Base : UiObservable.Abstract<GameUiState>(), GameUiObservable
}