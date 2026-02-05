package com.example.expertcoursequizgame.load.presentation

import com.example.expertcoursequizgame.R
import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.core.RunAsync
import com.example.expertcoursequizgame.di.ClearViewModel
import com.example.expertcoursequizgame.load.data.BackendException
import com.example.expertcoursequizgame.load.data.LoadRepository
import com.example.expertcoursequizgame.load.data.NoInternetConnectionException

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
                try {
                    repository.load()
                    clearViewModel.clear(LoadViewModel::class.java)
                    LoadUiState.Success
                } catch (e: Exception) {
                    when (e) {
                        is NoInternetConnectionException -> LoadUiState.ErrorRes()
                        is BackendException -> LoadUiState.Error(e.message)
                        else -> LoadUiState.ErrorRes(R.string.service_unavailable)
                    }
                }
            }) {
                observable.postUiState(it)
            }
        }
    }
}