package com.example.expertcoursequizgame.load

import com.example.expertcoursequizgame.load.data.QuestionAndChoicesCloud
import com.example.expertcoursequizgame.load.data.Response
import com.google.gson.Gson
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoadRepositoryTest {

    private val gson = Gson()

    @Test
    fun test() {
        val url = "https://opentdb.com/api.php?amount=10"
        val connection = URL(url).openConnection() as HttpsURLConnection
        try {
            val data = connection.inputStream.bufferedReader().use { it.readText() }
            assertTrue(data.isNotEmpty())

            val response = gson.fromJson(data, Response::class.java)
            val list = response.results
            assertEquals(10, list.size)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
    }
}

private class Response(
    val results: List<QuestionAndChoicesCloud>
)

private class QuestionAndChoicesCloud(
    private val question: String,
    private val correct_answer: String,
    private val incorrect_answer: List<String>
)