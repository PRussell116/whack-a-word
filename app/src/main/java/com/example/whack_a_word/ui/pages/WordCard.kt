package com.example.whack_a_word.ui.pages

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.whack_a_word.R
import com.example.whack_a_word.model.CardWord
import com.example.whack_a_word.ui.GameViewModel


@Composable
fun WordCard(word: CardWord,currentWord: CardWord,viewModel: GameViewModel){
    val ctx = LocalContext.current
    var textId: Int = 0
    var imgId: Int = 0
    var soundFileId = 0

    var shown by remember {mutableStateOf(true)}


    when(word){
        CardWord.APPLE -> {
            textId = R.string.apple
            imgId= R.drawable.fc_apple
            soundFileId = R.raw.fc_apple

        }
        CardWord.BANANA ->{
            textId = R.string.banana
            imgId = R.drawable.fc_banana
            soundFileId = R.raw.fc_banana
        }
        CardWord.BREAD->{
            textId = R.string.bread
            imgId = R.drawable.fc_bread
            soundFileId = R.raw.fc_bread
        }

        CardWord.CAKE->{
            textId = R.string.cake
            imgId = R.drawable.fc_cake
            soundFileId = R.raw.fc_cake
        }
        CardWord.CARROT ->{
            textId = R.string.carrot
            imgId = R.drawable.fc_carrot
            soundFileId = R.raw.fc_carrot
        }
        CardWord.EGG ->{
            textId = R.string.egg
            imgId = R.drawable.fc_egg
            soundFileId = R.raw.fc_egg
        }

        CardWord.ORANGE ->{
            textId = R.string.orange
            imgId = R.drawable.fc_orange
            soundFileId = R.raw.fc_orange
        }
        CardWord.POTATO ->{
            textId = R.string.potato
            imgId = R.drawable.fc_potato
            soundFileId = R.raw.fc_potato
        }
        CardWord.TOMATO ->{
            textId = R.string.tomato
            imgId = R.drawable.fc_tomato
            soundFileId = R.raw.fc_tomato
        }
        else -> Log.d("TAG","Empty")

    }
    val density = LocalDensity.current
    Card(modifier = Modifier
        .padding(5.dp)
        .height(150.dp)
        .clickable { viewModel.cardClicked(ctx, soundFileId, currentWord, word) }
        .fillMaxWidth()

    ){
        AnimatedVisibility(
            visible = shown,
            enter = slideInVertically(animationSpec = tween(durationMillis = 60000, easing = LinearOutSlowInEasing)) {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } ,
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ){
            Column(horizontalAlignment = CenterHorizontally,modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(),

                ) {
                Spacer(modifier = Modifier.padding(5.dp))
                Text(stringResource(textId))
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = stringResource(id = textId),
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
            }
        }

    }

}

