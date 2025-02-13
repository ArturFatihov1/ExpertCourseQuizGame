package com.example.expertcoursequizgame

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.expertcoursequizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: GameViewModel = (application as QuizApp).viewModel

        binding.firstChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            uiState.update(binding = binding)
        }
        binding.secondChoiceButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }
        binding.thirdChoiceButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            uiState.update(binding = binding)
        }
        binding.forthChoiceButton.setOnClickListener {
            uiState = viewModel.chooseForth()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check()
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            uiState.update(binding = binding)
        }
        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(
                    KEY,
                    GameUiState::class.java
                ) as GameUiState
            } else {
                savedInstanceState.getSerializable(KEY) as GameUiState
            }
        }
        uiState.update(binding = binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    companion object {
        private const val KEY = "uiState"
    }

}