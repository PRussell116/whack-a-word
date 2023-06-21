package com.example.whack_a_word.ui.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.whack_a_word.R
import com.example.whack_a_word.model.CardWord
import com.example.whack_a_word.ui.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun GameScreen(viewModel: GameViewModel

){
    val uiState by viewModel.gameUiState.collectAsState()

    Column( modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = CenterHorizontally

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            HoleCard(uiState.holeValues[0],uiState.currentWord,viewModel)
            HoleCard(uiState.holeValues[1],uiState.currentWord,viewModel)
            HoleCard(uiState.holeValues[2],uiState.currentWord,viewModel)


        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            HoleCard(uiState.holeValues[3],uiState.currentWord,viewModel)
            HoleCard(uiState.holeValues[4],uiState.currentWord,viewModel)

        }

        // display tick if correct card clicked and hide after 1500 milliseconds
        if(uiState.correctClicked){
            LaunchedEffect(key1= Unit ){
                delay(1500)
                viewModel.changeCorrect(false)

            }

            Popup(alignment = Center) {
                Image(painter = painterResource(R.drawable.tick ), contentDescription = "Correct!")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HoleCard(cardWord: CardWord,currentWord:CardWord,viewModel: GameViewModel) {

    Column(
        Modifier
            .width(100.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        AnimatedContent(targetState = cardWord, transitionSpec = {

            fadeIn(animationSpec = tween(2000)) + slideInVertically(
                animationSpec = tween(2000),
                initialOffsetY = { height -> height + height }) with
                    slideOutVertically(
                        animationSpec = tween(2000),
                        targetOffsetY = { height -> height + height })

        }) { targetState ->

            WordCard(word = targetState, currentWord, viewModel)
        }
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.hole),
            contentDescription = "Image of a hole",
            modifier = Modifier

                .clip(CircleShape)
                .height(50.dp)
                .fillMaxSize()
                .align(CenterHorizontally)
        )
    }
}








