package com.example.expertcoursequizgame

import android.app.Application
import android.content.Context
import com.example.expertcoursequizgame.game.GameRepository
import com.example.expertcoursequizgame.game.GameViewModel
import com.example.expertcoursequizgame.stats.GameOverViewModel
import com.example.expertcoursequizgame.stats.StatsRepository

class QuizApp : Application(), ProvideViewModel {


    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        val make = ProvideViewModel.Make(
            object : ClearViewModel {
                override fun clear(viewModelClass: Class<out MyViewModel>) {
                    factory.clear(viewModelClass)
                }
            },
            Core(this)
        )
        factory = ManageViewModels.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T {
        return factory.makeViewModel(clasz)
    }
}

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}

interface ManageViewModels : ProvideViewModel, ClearViewModel {
    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val viewModelsMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T =
            if (viewModelsMap[clasz] == null) {
                val viewModel = make.makeViewModel(clasz)
                viewModelsMap[clasz] = viewModel
                viewModel
            } else
                viewModelsMap[clasz] as T

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            viewModelsMap[viewModelClass] = null
        }

    }
}

interface ProvideViewModel {
    fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T


    class Make(
        private val clearViewModel: ClearViewModel,
        private val core: Core
    ) : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T {
            return when (clasz) {
                GameViewModel::class.java -> {
                    val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
                    val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)
                    GameViewModel(
                        clearViewModel,
                        GameRepository.Base(
                            corrects,
                            incorrects,
                            IntCache.Base(core.sharedPreferences, "indexKey", 0),
                            IntCache.Base(core.sharedPreferences, "userChoiceIndexKey", -1)
                        )
                    )
                }

                GameOverViewModel::class.java -> {
                    val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
                    val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)

                    GameOverViewModel(
                        clearViewModel,
                        StatsRepository.Base(
                            corrects,
                            incorrects
                        )
                    )
                }

                else -> throw IllegalStateException("unknown class $clasz")
            } as T
        }

    }
}

class Core(val context: Context) {

    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
}
