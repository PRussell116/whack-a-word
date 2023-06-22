package com.example.whack_a_word.data

import com.example.whack_a_word.model.CardWord

/**
 * Data class for the ui state has values, holeValues a list of cardWord which holds the values of each hole
 *
 * currentWord the word which the user must guess
 *
 * correctClicked boolean describing if the correct word has been clicked
 *
 *  succecssCount int for number of times the correct answer has been clicked cannot reach larger than 3
 *
 *  moleCount int for the number of cards to be displayed
 *
 */
data class CardUiState(
    val holeValues :List<CardWord> = listOf(CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY,CardWord.EMPTY),


    val currentWord: CardWord = CardWord.EMPTY,
    val correctClicked: Boolean = false,

    val successCount:Int = 0,
    val moleCount:Int = 1,

)
