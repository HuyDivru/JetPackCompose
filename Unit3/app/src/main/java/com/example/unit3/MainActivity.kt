package com.example.unit3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit3.ui.theme.Unit3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SolutionButton()
                }
            }
        }
    }
}
@Composable
fun SolutionButton(){
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            val intent=Intent(context, AffirmationCard::class.java)
            context.startActivity(intent)
        }, modifier = Modifier
            .padding(10.dp)
            .background(Color.Blue)) {
            Text(text = "AffirmationCard", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            val intent=Intent(context, GridGame::class.java)
            context.startActivity(intent)
        }, modifier = Modifier.padding(10.dp).background(Color.Blue)) {
            Text(text = "GridGame", fontSize = 20.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Unit3Theme {
        SolutionButton()
    }
}