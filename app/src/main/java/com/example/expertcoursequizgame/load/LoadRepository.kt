package com.example.expertcoursequizgame.load

interface LoadRepository {
    fun load(resultCallback: (LoadResult) -> Unit)
}