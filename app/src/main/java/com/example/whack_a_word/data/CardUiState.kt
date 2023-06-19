package com.example.whack_a_word.data

import com.example.whack_a_word.model.CardWord

data class CardUiState(
    val holeValues :List<CardWord> = listOf<CardWord>(CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY),


    val currentWord: CardWord = CardWord.EMPTY,
    val correctClicked: Boolean = false,

    val successCount:Int = 0,
    val moleCount:Int = 1,

)
