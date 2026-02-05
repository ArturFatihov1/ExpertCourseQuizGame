package com.example.expertcoursequizgame.load.presentation

import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.core.RunAsync
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.load.data.LoadRepository

class LoadViewModel(
    private val repository: LoadRepository,
    observable: LoadUiObservable,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Abstract<LoadUiState>(observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            runAsync.handleAsync(viewModelScope, {
                val result = repository.load()
                if (result.isSuccessful()) {
                    clearViewModel.clear(LoadViewModel::class.java)
                    LoadUiState.Success
                } else
                    LoadUiState.Error(result.message())
            }) {
                observable.postUiState(it)
            }
        }
    }

}