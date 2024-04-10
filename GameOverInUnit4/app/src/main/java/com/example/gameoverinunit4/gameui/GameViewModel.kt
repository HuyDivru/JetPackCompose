package com.example.gameoverinunit4.gameui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gameoverinunit4.data.MAX_NO_OF_WORDS
import com.example.gameoverinunit4.data.SCORE_INCREASE
import com.example.gameoverinunit4.data.allWorks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel:ViewModel(){
    //show state of user
    private val _uiState= MutableStateFlow(GameUiState())
    val uiState:StateFlow<GameUiState> =_uiState.asStateFlow()

    var userGuess by mutableStateOf("")
    private set

    private var usedWords:MutableSet<String> = mutableSetOf()
    private lateinit var  currentWord:String

    init {
        resetGame()
    }

     fun resetGame() {
        usedWords.clear()
        _uiState.value= GameUiState(currentScrambleWord = pickRandWordAndShuffle())
    }

    private fun pickRandWordAndShuffle(): String {
        currentWord= allWorks.random()
        return if(usedWords.contains(currentWord)){
            pickRandWordAndShuffle()
        }
        else{
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(currentWord: String): String {
        val tempWord=currentWord.toCharArray()
        tempWord.shuffle()
        while (String(tempWord)==currentWord){
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun updateUserGuess(guessWord:String){
          userGuess=guessWord
    }
    fun checkUserGuess(){
        if(userGuess.equals(currentWord, ignoreCase = true)){
            val updateScore=_uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updateScore)
        }
        else{
            _uiState.update {
                currentState ->
                currentState.copy(isGuessWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updateScore: Int) {
        if(usedWords.size== MAX_NO_OF_WORDS){
            _uiState.update {
                currentState ->
                currentState.copy(isGuessWordWrong = false, score = updateScore, isGameOver = true)
            }
        }
        else{
            _uiState.update {
                currentState ->currentState.copy(
                isGuessWordWrong = false,
                currentScrambleWord = pickRandWordAndShuffle()
                , currentWord = currentState.currentWord.inc(),
                score = updateScore
                )
            }
        }
    }

    fun skipWord(){
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

}