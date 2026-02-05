package com.example.expertcoursequizgame.core

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface RunAsync {
    fun <T : Any> handleAsync(
        coroutineScope: CoroutineScope,
        heavyOperation: suspend () -> T,
        uiUpdate: (T) -> Unit
    )

    class Base() : RunAsync {
        fun <T : Any> handleAsyncOld(
            heavyOperation: () -> T,
            uiUpdate: (T) -> Unit
        ) {
            Thread {
                val result = heavyOperation.invoke()
                Handler(Looper.getMainLooper()).post {
                    uiUpdate.invoke(result)
                }
            }.start()
        }

        override fun <T : Any> handleAsync(
            coroutineScope: CoroutineScope,
            heavyOperation: suspend () -> T,
            uiUpdate: (T) -> Unit
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                val result = heavyOperation.invoke()
                withContext(Dispatchers.Main) {
                    uiUpdate.invoke(result)
                }
            }
        }
    }
}