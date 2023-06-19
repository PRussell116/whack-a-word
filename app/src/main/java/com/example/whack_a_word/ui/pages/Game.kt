package com.example.whack_a_word.ui.pages

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val ctx = LocalContext.current
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

        // display tick if correct card clicked and hide after 2500 milliseconds
        if(uiState.correctClicked){
            LaunchedEffect(key1= Unit ){
                delay(1500)
                viewModel.changeCorrect()

            }

            Popup(alignment = Center) {
                Image(painter = painterResource(R.drawable.tick ), contentDescription = "Correct!")
            }


        }

        
    }
    Button(onClick = {
        val word = viewModel.newGameRound(ctx)
        playSound(word, ctx)})
    {
        Text(text = "change word")
    }

}
/**
 * Read word when a new word is chosen
 * */
fun playSound(word: CardWord,ctx:Context) {
    val soundId = when(word){
        CardWord.APPLE -> R.raw.fc_apple
        CardWord.BANANA -> R.raw.fc_banana
        CardWord.BREAD ->R.raw.fc_bread
        CardWord.CAKE->R.raw.fc_cake
        CardWord.CARROT->R.raw.fc_carrot
        CardWord.EGG->R.raw.fc_egg
        CardWord.ORANGE->R.raw.fc_orange
        CardWord.POTATO->R.raw.fc_potato
        else -> R.raw.fc_tomato
    }
    MediaPlayer.create(ctx,soundId).start()

}



@Composable
fun HoleCard(cardWord: CardWord,currentWord:CardWord,viewModel: GameViewModel){
    Card(modifier = Modifier
        .padding(5.dp)
        .width(200.dp)
        .height(300.dp)) {
        Column(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            if(cardWord != CardWord.EMPTY){
                WordCard(word = cardWord,currentWord,viewModel)
            }

            Image(
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.hole),
                contentDescription = "Image of a hole",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .align(CenterHorizontally))

        }
    }
}



