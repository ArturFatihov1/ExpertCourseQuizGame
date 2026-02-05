package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.core.MyViewModel
import com.example.expertcoursequizgame.game.di.ProvideGameViewModel
import com.example.expertcoursequizgame.load.di.ProvideLoadViewModel
import com.example.expertcoursequizgame.stats.di.ProvideGameOverViewModel

interface ProvideViewModel {
    fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T


    class Make(core: Core) : ProvideViewModel {
        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideLoadViewModel(core, chain)
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T =
            chain.makeViewModel(clasz)
    }

    class Error : ProvideViewModel {
        override fun <S : Any, T : MyViewModel<S>> makeViewModel(clasz: Class<T>): T {
            throw IllegalStateException("unknown class $clasz")
        }
    }

}