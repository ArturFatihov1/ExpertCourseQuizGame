package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.core.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel<*>>)
}