package com.example.gameoverinunit4.gameui

import android.app.Activity
import android.app.GameState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gameoverinunit4.R


@Composable
fun GameScreen(gameViewModel: GameViewModel=viewModel()){
    val mediumPadding= dimensionResource(id = R.dimen.padding_medium)

    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(mediumPadding), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.titleLarge)
        GameLayout(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(mediumPadding),
            onUserGuessChanged={gameViewModel.updateUserGuess(it)},
            wordCout=gameUiState.currentWord,
            userGuess=gameViewModel.userGuess,
            onKeyboardDone={gameViewModel.checkUserGuess()},
            currnetScramebleWord=gameUiState.currentScrambleWord,
            isGuessWrong=gameUiState.isGuessWordWrong
            )
      Column(modifier = Modifier
          .fillMaxWidth()
          .padding(mediumPadding), verticalArrangement = Arrangement.spacedBy(mediumPadding), horizontalAlignment = Alignment.CenterHorizontally) {
          Button(onClick = { gameViewModel.checkUserGuess() }, modifier = Modifier.fillMaxWidth()) {
              Text(text = stringResource(id = R.string.submit), style = MaterialTheme.typography.titleSmall)
          }
          OutlinedButton(onClick = { gameViewModel.skipWord() }, modifier = Modifier.fillMaxWidth()) {
              Text(text = stringResource(id = R.string.skip), style = MaterialTheme.typography.titleSmall)
          }
          GameStatus(score=gameUiState.score, modifier=Modifier.padding(mediumPadding))

          if(gameUiState.isGameOver){
              FinalScoreDialog(score = gameUiState.score, onPlayAgain = { gameViewModel.resetGame() })
          }
      }

    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier) {
    Card(modifier = modifier) {
        Text(text = stringResource(id = R.string.score,score), style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    onUserGuessChanged: (String) -> Unit,
    wordCout: Int,
    userGuess: String,
    onKeyboardDone: () -> Unit,
    currnetScramebleWord: String,
    isGuessWrong: Boolean
) {
    val mediumPadding= dimensionResource(id = R.dimen.padding_medium)
    Card(modifier = Modifier, elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
            Text(text = stringResource(id = R.string.word_count,wordCout)
                , style = MaterialTheme.typography.titleMedium,
                modifier= Modifier
                    .clip(shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceTint)
                    .align(alignment = Alignment.End)
                , color = MaterialTheme.colorScheme.onPrimary)
            Text(text = currnetScramebleWord, style = MaterialTheme.typography.displayMedium)
            Text(text = stringResource(id = R.string.instruction), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(value = userGuess, onValueChange =onUserGuessChanged
                , singleLine = true, shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    if(isGuessWrong){
                        Text(text = stringResource(id = R.string.wrong_guess))
                    }
                    else{
                        Text(text = stringResource(id = R.string.enter_your_word))
                    }
                },
                isError = false,
                keyboardActions = KeyboardActions (
                    onDone={onKeyboardDone()}
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface))
        }
    }
}
@Composable
fun FinalScoreDialog(score:Int,onPlayAgain:() ->Unit,modifier: Modifier=Modifier){
    val activity =(LocalContext.current as Activity)
    AlertDialog(onDismissRequest = { /*TODO*/ }, title = { Text(text = stringResource(id = R.string.congratulations))},
    text = { Text(text = stringResource(id = R.string.you_scored,score))},
    modifier = modifier,
    dismissButton = {
        TextButton(onClick = {activity.finish()}) {
            Text(text = stringResource(id = R.string.exit))
        }
    },
    confirmButton = {TextButton(onClick = onPlayAgain) {
        Text(text = stringResource(id = R.string.play_again))
    }
    }) 

    
}
