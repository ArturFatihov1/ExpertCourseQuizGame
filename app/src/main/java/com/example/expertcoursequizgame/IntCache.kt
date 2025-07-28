package com.example.expertcoursequizgame

import android.content.SharedPreferences

interface IntCache {
    fun read(): Int
    fun save(newValue: Int)

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Int
    ) : IntCache {

        override fun read(): Int {
            return sharedPreferences.getInt(key, defaultValue)
        }

        override fun save(newValue: Int) {
            return sharedPreferences.edit().putInt(key, newValue).apply()
        }

    }
}