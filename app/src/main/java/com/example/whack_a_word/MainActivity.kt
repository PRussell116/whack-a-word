package com.example.whack_a_word

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.whack_a_word.ui.GameViewModel
import com.example.whack_a_word.ui.pages.GameScreen
import com.example.whack_a_word.ui.theme.WhackawordTheme

class MainActivity : ComponentActivity() {
    // viewModel controlling the state
    val viewModel = GameViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhackawordTheme {
                // grass background
                Image(
                    painter = painterResource(R.drawable.cartoon_green_texture_grass),
                    contentDescription ="", modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds)

                GameScreen(viewModel = viewModel)
                viewModel.newGameRound(LocalContext.current)
            }

        }

    }


}

