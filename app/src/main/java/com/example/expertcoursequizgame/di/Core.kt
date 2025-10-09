package com.example.expertcoursequizgame.di

import android.content.Context

class Core(val context: Context, val clearViewModel: ClearViewModel) {

    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
}