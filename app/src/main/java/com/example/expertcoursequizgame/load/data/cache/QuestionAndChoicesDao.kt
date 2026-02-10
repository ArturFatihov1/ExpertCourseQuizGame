package com.example.expertcoursequizgame.load.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionAndChoicesDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveQuestions(questions: List<QuestionCache>)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveIncorrects(incorrects: List<IncorrectCache>)

    @Query("SELECT * FROM question WHERE id=:questionId")
    suspend fun question(questionId: Int): QuestionCache

    @Query("SELECT * FROM incorrects WHERE questionId = :questionId")
    suspend fun incorrects(questionId: Int): List<IncorrectCache>
}