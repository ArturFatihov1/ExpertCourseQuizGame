package com.example.expertcoursequizgame.game

data class QuestionAndChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
)