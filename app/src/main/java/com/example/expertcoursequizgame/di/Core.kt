package com.example.expertcoursequizgame.di

import android.content.Context
import com.google.gson.Gson

class Core(val context: Context, val clearViewModel: ClearViewModel) {

    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
    val gson = Gson()
}