package com.example.expertcoursequizgame.load

interface UiObservable {
    fun register(observer: (LoadUiState) -> Unit)

    fun unregister()

    fun postUiState(uiState: LoadUiState)

    class Base() : UiObservable {

        private var uiStateCached: LoadUiState? = null
        private var observerCached: ((LoadUiState) -> Unit)? = null // aka fragment

        override fun register(observer: (LoadUiState) -> Unit) { //onResume
            observerCached = observer
            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached)
                uiStateCached = null
            }
        }

        override fun unregister() { //onPause
            observerCached = null
        }

        override fun postUiState(uiState: LoadUiState) { //pinged by ViewModel asynchronously
            if (observerCached == null) {  //onPause was called, but onResume still not
                uiStateCached = uiState //save ui state till new fragment become onResume
            } else {
                observerCached!!.invoke(uiStateCached) //after onResume and till onPause
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
