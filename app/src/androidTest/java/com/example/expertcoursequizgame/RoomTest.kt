package com.example.expertcoursequizgame

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expertcoursequizgame.load.data.cache.IncorrectCache
import com.example.expertcoursequizgame.load.data.cache.QuestionAndChoicesDao
import com.example.expertcoursequizgame.load.data.cache.QuestionAndChoicesDatabase
import com.example.expertcoursequizgame.load.data.cache.QuestionCache
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var dao: QuestionAndChoicesDao
    private lateinit var database: QuestionAndChoicesDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            QuestionAndChoicesDatabase::class.java
        ).build()
        dao = database.dao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun test() = runBlocking {
        dao.saveQuestions(
            listOf(
                QuestionCache(1, "1", "one"),
                QuestionCache(2, "2", "two"),
            )
        )
        dao.saveIncorrects(
            listOf(
                IncorrectCache(questionId = 1, choice = "incorrect11"),
                IncorrectCache(questionId = 1, choice = "incorrect12"),
                IncorrectCache(questionId = 2, choice = "incorrect21"),
                IncorrectCache(questionId = 2, choice = "incorrect22"),
                IncorrectCache(questionId = 2, choice = "incorrect23"),
            )
        )

        var actual: Any = dao.question(1)
        var expected: Any = QuestionCache(1, "1", "one")
        assertEquals(expected, actual)

        actual = dao.question(2)
        expected = QuestionCache(2, "2", "two")
        assertEquals(expected, actual)

        actual = dao.incorrects(1)
        expected = listOf(
            IncorrectCache(questionId = 1, choice = "incorrect11"),
            IncorrectCache(questionId = 1, choice = "incorrect12"),
        )
        assertEquals(expected, actual)

        actual = dao.incorrects(2)
        expected = listOf(
            IncorrectCache(questionId = 2, choice = "incorrect21"),
            IncorrectCache(questionId = 2, choice = "incorrect22"),
            IncorrectCache(questionId = 2, choice = "incorrect23"),
        )
        assertEquals(expected, actual)
    }
}