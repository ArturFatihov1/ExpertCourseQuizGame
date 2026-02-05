package com.example.expertcoursequizgame.load.data

import com.example.expertcoursequizgame.core.IntCache
import com.example.expertcoursequizgame.load.data.cache.IncorrectCache
import com.example.expertcoursequizgame.load.data.cache.QuestionAndChoicesDao
import com.example.expertcoursequizgame.load.data.cache.QuestionCache
import com.example.expertcoursequizgame.load.data.cloud.CloudDataSource
import kotlinx.coroutines.delay
import java.io.IOException
import java.net.UnknownHostException

interface LoadRepository {

    suspend fun load()

    class Base(
        private val index: IntCache,
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: QuestionAndChoicesDao,
    ) : LoadRepository {

        override suspend fun load() {
            try {
                val dataList = cloudDataSource.load()
                val incorrects = mutableListOf<IncorrectCache>()
                val questions = dataList.mapIndexed { index, data ->
                    val temporary = data.incorrectAnswers.map {
                        IncorrectCache(questionId = index, choice = it)
                    }
                    incorrects.addAll(temporary)
                    QuestionCache(index, data.question, data.correctAnswer)
                }
                cacheDataSource.saveQuestions(questions)
                cacheDataSource.saveIncorrects(incorrects)
                index.save(0)
            } catch (e: Exception) {
                if (e is IOException)
                    throw NoInternetConnectionException()
                if (e is IllegalArgumentException)
                    throw BackendException(e.message ?: "")
                throw ServiceUnavailable()
            }
        }
    }

    class Fake : LoadRepository {

        private var count = 0
        override suspend fun load() {
            delay(3000)
            if (count == 0) {
                count++
                throw UnknownHostException()
            } else {
                LoadResult.Success
            }
        }

    }
}

class NoInternetConnectionException : Exception()

class BackendException(override val message: String) : Exception(message)

class ServiceUnavailable : Exception()