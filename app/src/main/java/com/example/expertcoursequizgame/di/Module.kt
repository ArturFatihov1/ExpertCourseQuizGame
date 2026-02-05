package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.core.MyViewModel

interface Module<T : MyViewModel<*>> {
    fun viewModel(): T
}