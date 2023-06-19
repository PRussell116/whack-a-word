package com.example.whack_a_word

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.whack_a_word.ui.GameViewModel
import com.example.whack_a_word.ui.pages.GameScreen
import com.example.whack_a_word.ui.theme.WhackawordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhackawordTheme {
                GameScreen(viewModel = GameViewModel())
            }

        }
    }
}

