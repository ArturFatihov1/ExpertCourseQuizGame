package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel(): T
}