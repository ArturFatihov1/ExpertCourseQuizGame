package com.example.expertcoursequizgame.load.presentation

interface LoadUiObservable : UiObservable<LoadUiState> {
    class Base : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}