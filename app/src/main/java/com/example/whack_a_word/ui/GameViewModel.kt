package com.example.whack_a_word.ui
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.whack_a_word.R
import com.example.whack_a_word.data.CardUiState
import com.example.whack_a_word.model.CardWord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel:ViewModel() {

    private val _gameUiState = MutableStateFlow(CardUiState())
    val gameUiState: StateFlow<CardUiState> = _gameUiState.asStateFlow()

    var mMediaPlayer: MediaPlayer = MediaPlayer()
    var correctPlayer:MediaPlayer = MediaPlayer()
    var wordReader : MediaPlayer = MediaPlayer()

    fun cardClicked(context: Context, soundFile: Int, currentWord: CardWord, wordOnCardWord: CardWord){
        // read the word picked


        mMediaPlayer = MediaPlayer.create(context, soundFile)
        mMediaPlayer.isLooping = false
        mMediaPlayer.start()


        // if correct is clicked
        if (currentWord == wordOnCardWord){
            // play the sound
            mMediaPlayer.setOnCompletionListener {
                correctPlayer = MediaPlayer.create(context,R.raw.correct)
                correctPlayer.start()

           }


            //increment success count / increase mole count
            if (gameUiState.value.successCount < 2){
                _gameUiState.update { currentState ->currentState.copy(successCount = currentState.successCount+1) }
            }else if(gameUiState.value.moleCount <3){
                _gameUiState.update { currentState -> currentState.copy(successCount = 0, moleCount = currentState.moleCount+1) }
            }
            _gameUiState.update { currentState -> currentState.copy(correctClicked = true) }
        }

        newGameRound(context)
    }

    fun  changeCorrect(){
        _gameUiState.update { currentState -> currentState.copy(correctClicked = !currentState.correctClicked) }
    }

    /**
     * Function that handles setting the state for which words are in the holes and which is the correct word
     */
    fun newGameRound(context: Context):CardWord{
        // make list of all possible words
        val wordList = CardWord.values().toMutableList()
        wordList.remove(CardWord.EMPTY)


        var newWords : MutableList<CardWord> = ArrayList()

        // Pick correct word mole
        val newCorrectWord = pickRandomWordAndRemoveFromList(wordList)
        newWords.add(newCorrectWord)

        // choose other incorrect moles (cant choose already used words)
        for(i in 0 .. gameUiState.value.moleCount -2){
            newWords.add(pickRandomWordAndRemoveFromList(wordList))

        }
        // add remaining empty values
        for(i in newWords.size .. 4){
            newWords.add(CardWord.EMPTY)
        }
        // change order to random
        newWords.shuffle()


        // update the state
        _gameUiState.update { currentState -> currentState.copy(holeValues = newWords, currentWord = newCorrectWord) }

       // read the currentWord
        val soundFile = when(newCorrectWord){
            CardWord.APPLE -> R.raw.fc_apple
            CardWord.BANANA -> R.raw.fc_banana
            CardWord.BREAD -> R.raw.fc_bread
            CardWord.CAKE -> R.raw.fc_cake
            CardWord.CARROT -> R.raw.fc_carrot
            CardWord.EGG -> R.raw.fc_egg
            CardWord.ORANGE -> R.raw.fc_orange
            CardWord.POTATO -> R.raw.fc_potato
            else -> R.raw.fc_tomato
        }


        // if correct word is clicked play correct sound then new word else play just new word
        mMediaPlayer.setOnCompletionListener {

            if(gameUiState.value.correctClicked){
                correctPlayer = MediaPlayer.create(context,R.raw.correct)
                correctPlayer.start()
                correctPlayer.setOnCompletionListener {
                    wordReader = MediaPlayer.create(context,soundFile)
                    wordReader.start()
                }

            }else{
                wordReader = MediaPlayer.create(context,soundFile)
                wordReader.start()
            }



        }






        return newCorrectWord

    }

    fun pickRandomWordAndRemoveFromList(wordList: MutableList<CardWord>):CardWord{
        val chosenWord = wordList.random()
        wordList.remove(chosenWord)
        return chosenWord

    }


}