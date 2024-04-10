package com.example.gameoverinunit4.gameui

import androidx.compose.runtime.Composable


data class GameUiState (
        val currentScrambleWord:String="",
        val currentWord:Int=1,
        val score:Int=0,
        val isGuessWordWrong:Boolean =false,
        val isGameOver:Boolean  =false
)