package com.example.expertcoursequizgame.di

import android.content.Context
import com.example.expertcoursequizgame.core.RunAsync
import com.example.expertcoursequizgame.load.data.cache.CacheModule

class Core(val context: Context, val clearViewModel: ClearViewModel) {

    val runAsync: RunAsync = RunAsync.Base()
    val runUiTests = true
    val size = 10
    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)

    val cacheModule: CacheModule = CacheModule.Base(context)
}