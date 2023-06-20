package com.example.whack_a_word.ui.pages

import android.util.Log
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.whack_a_word.R
import com.example.whack_a_word.model.CardWord
import com.example.whack_a_word.ui.GameViewModel


@Composable
fun WordCard(word: CardWord,currentWord: CardWord,viewModel: GameViewModel){
    val ctx = LocalContext.current
    var textId = 0
    var imgId = 0
    var soundFileId = 0


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
    if(word!=CardWord.EMPTY){
        Card(modifier = Modifier
            .padding(5.dp)
            .height(150.dp)
            .clickable { viewModel.cardClicked(ctx, soundFileId, currentWord, word) }
            .fillMaxWidth()

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

