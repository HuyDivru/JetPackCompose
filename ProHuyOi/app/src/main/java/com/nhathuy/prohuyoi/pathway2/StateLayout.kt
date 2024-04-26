package com.nhathuy.prohuyoi.pathway2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhathuy.prohuyoi.pathway2.ui.theme.ProHuyOiTheme

class StateLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProHuyOiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun WaterScreen(modifier: Modifier=Modifier){
    WaterCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier=Modifier){

    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable {
            mutableStateOf(0)
        }
        if(count>0){
            var showTask by remember {
                mutableStateOf(true)
            }
            if(showTask){
                WellnessTaskItem(onClose={showTask=false},taskName="Have you taken your 15 minutes walk today")
            }
            Text(text = "You've had $count glasses")
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count<10) {
                Text(text = "Add one")
            }
            Button(onClick = {count=0}, modifier = modifier.padding(start = 8.dp)) {
                Text(text = "Clear water count")
            }
        }
    }
    
}

@Composable
fun WellnessTaskItem(onClose: () -> Unit, taskName: String,modifier: Modifier=Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName, modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp))
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription ="Close" )
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier=Modifier){
    var count by rememberSaveable{
        mutableStateOf(0)
    }
    StatelessCounter(count,{count++},modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if(count<0){
            Text(text = "You've had $count glasses.")
        }
        Button(onClick = onIncrement,Modifier.padding(top = 8.dp),enabled = count<10) {
            Text(text = "Add one")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ProHuyOiTheme {
        WaterCounter()
    }
}