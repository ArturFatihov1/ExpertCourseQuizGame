package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.core.MyViewModel

abstract class AbstractProvideViewModel(
    protected val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out MyViewModel<*>>
) : ProvideViewModel {

    override fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T {
        return if (clasz == viewModelClass)
            module().viewModel() as T
        else
            nextChain.makeViewModel(clasz)

    }

    protected abstract fun module(): Module<*>
}