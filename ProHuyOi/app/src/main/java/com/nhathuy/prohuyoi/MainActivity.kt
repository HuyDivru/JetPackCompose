package com.nhathuy.prohuyoi

import android.nfc.cardemulation.CardEmulation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhathuy.prohuyoi.ui.theme.ProHuyOiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProHuyOiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Composable
fun MyApp(modifier: Modifier=Modifier){
    var boardingChange by remember {
        mutableStateOf(true)
    }
    Surface(modifier = modifier.fillMaxSize()) {
        if(boardingChange){
            BoardingScreen (boardingChange= { boardingChange = false })
        }
        else{
            Greetings()
        }
    }
}

@Composable
fun BoardingScreen(boardingChange: () -> Unit,modifier: Modifier=Modifier) {
    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(horizontal = 5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome you to huy")
        Button(onClick = boardingChange, modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = "Continue", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun Greetings(names:List<String> = List(100) {"$it"},modifier: Modifier=Modifier) {
    LazyColumn(modifier = modifier.padding(vertical = 5.dp)){
        items(items=names){name-> 
            ContentCard(name=name)
        }
        

    }
}

@Composable
fun ContentCard(name: String,modifier: Modifier=Modifier) {
    Card(modifier=modifier.padding(vertical = 4.dp, horizontal = 5.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
        Greeting(name)
    }
}

@Composable
fun Greeting(name: String) {
    var expended by rememberSaveable {
        mutableStateOf(false)
    }
    
    Row(modifier = Modifier
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
            Text(text = "Hello, ")
            Text(text = name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp))
            if(expended){
                Text(text = "Cạnh phải của vùng chứa Text dành cho các bảng chữ cái từ phải sang trái, chẳng hạn như chữ Ả Rập hoặc chữ Do Thái".repeat(4))
            }

        }
        ElevatedButton(onClick = {expended=!expended}) {
            Text(text = if(expended)"Show less" else "Show More")
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProHuyOiTheme {
        MyApp()
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewBoadring() {
    ProHuyOiTheme {
        BoardingScreen(boardingChange = {})
    }
}
