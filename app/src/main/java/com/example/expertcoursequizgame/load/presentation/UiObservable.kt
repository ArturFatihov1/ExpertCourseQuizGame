package com.example.expertcoursequizgame.load.presentation

interface UiObservable<T : Any> {
    fun register(observer: (T) -> Unit)

    fun unregister()

    fun postUiState(uiState: T)

    abstract class Abstract<T : Any> : UiObservable<T> {

        private var uiStateCached: T? = null
        private var observerCached: ((T) -> Unit)? = null // aka fragment

        override fun register(observer: (T) -> Unit) { //onResume
            observerCached = observer
            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached!!)
                uiStateCached = null
            }
        }

        override fun unregister() { //onPause
            observerCached = null
        }

        override fun postUiState(uiState: T) { //pinged by ViewModel asynchronously
            if (observerCached == null) {  //onPause was called, but onResume still not
                uiStateCached = uiState //save ui state till new fragment become onResume
            } else {
                observerCached!!.invoke(uiState) //after onResume and till onPause
                uiStateCached = null
            }
        }
    }
}

/**
1. register aka fragment onResume
2. some time lasted
3. postUiState -> immediately update ui
 **/

/**
1. register aka fragment onResume
2. some time lasted
3. unregister aka fragment onPause
4. some time lasted
5. postUiState: cache uiState and wait till register aka onResume new fragment
6. register new fragment aka onResume: update ui now! and clear the cache
 **/
