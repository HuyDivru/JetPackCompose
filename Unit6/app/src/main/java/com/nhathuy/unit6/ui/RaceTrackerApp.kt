package com.nhathuy.unit6.ui

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhathuy.unit6.R
import com.nhathuy.unit6.ui.theme.Unit6Theme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun RaceTrackerApp() {
    val playerOne= remember {
        RaceParticipant(name = "Player 1", progressIncrement = 1)
    }
    val playerTwo = remember {
        RaceParticipant(name = "Player 2", progressIncrement = 2)
    }
    var raceParticipant by remember {
        mutableStateOf(false)
    }
    var raceInProgress by remember {
        mutableStateOf(false)
    }
    if(raceParticipant){
        LaunchedEffect(playerOne, playerTwo){
            coroutineScope {
                launch {
                    playerOne.run()
                }
                launch {
                    playerTwo.run()
                }
                raceInProgress = false
            }
        }
    }

    RaceTrackerScreen(
        playerOne=playerOne,
        playerTwo=playerTwo,
        isRunning=raceInProgress,
        onRunStateChange={raceInProgress=it},
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
    )
}

@Composable
fun RaceTrackerScreen(playerOne: RaceParticipant, playerTwo: RaceParticipant, isRunning: Boolean, onRunStateChange: (Boolean) -> Unit, modifier: Modifier) {
    Column(modifier=modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.run_a_race), style = MaterialTheme.typography.headlineSmall,)
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.ic_walk), contentDescription =null,modifier=Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)) )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
            StatusIndicator(
                participantName=playerOne.name,
                currentProgress=playerOne.currentProgress,
                maxProgress= stringResource(id =
                R.string.progress_percentage,
                playerOne.maxProgress),
                progressFactor=playerOne.progressFactor,
                modifier=Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
            StatusIndicator(
                participantName=playerTwo.name,
                currentProgress=playerTwo.currentProgress,
                maxProgress= stringResource(id =
                R.string.progress_percentage,
                    playerTwo.maxProgress),
                progressFactor=playerTwo.progressFactor,
                modifier=Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
            RaceButton(
                isRunning=isRunning,
                onRunStateChange=onRunStateChange,
                onReset={
                    playerOne.reset()
                    playerTwo.reset()
                    onRunStateChange(false)
                },modifier=Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RaceButton(isRunning: Boolean, onRunStateChange: (Boolean) -> Unit, onReset: () -> Unit, modifier: Modifier) {
    Column(modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_medium)), verticalArrangement = Arrangement.spacedBy(
        dimensionResource(id = R.dimen.padding_medium))) {
        Button(onClick = {onRunStateChange(!isRunning)},modifier=Modifier.fillMaxWidth()) {
            Text(if(isRunning) stringResource(id = R.string.pause) else stringResource(id = R.string.start))
        }
        OutlinedButton(onClick = onReset, modifier =Modifier.fillMaxWidth()) {
            Text(stringResource(id = R.string.reset))
        }
    }
}

@Composable
fun StatusIndicator(participantName: String,
                    currentProgress: Int, maxProgress:
                    String,
                    progressFactor: Float,
                    modifier: Modifier) {
    Row(modifier=modifier) {
        Text(text = participantName,modifier=Modifier.padding(end= dimensionResource(id = R.dimen.padding_medium)))
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))) {
            LinearProgressIndicator(
                progress = progressFactor,
                modifier= Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(id = R.string.progress_percentage,currentProgress), textAlign = TextAlign.Start,
                modifier=Modifier.weight(1f))
                Text(text = maxProgress, textAlign = TextAlign.End,modifier=Modifier.weight(1f))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RaceTrackerAppPreview() {
    Unit6Theme {
        RaceTrackerApp()
    }
}