package com.example.expertcoursequizgame.di

import com.example.expertcoursequizgame.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}