package com.example.unit2

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit2.ui.theme.Unit2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit2Theme {
                    DiceWithButtonAndImage(
                        )
            }
        }
    }
}
@Composable
fun DiceWithButtonAndImage(modifier: Modifier=Modifier){
   var result by remember { mutableStateOf(1) }

   val imageResource :Painter = painterResource(id = when (result){
       1 -> R.drawable.dice_1
       2 -> R.drawable.dice_2
       3 -> R.drawable.dice_3
       4 -> R.drawable.dice_4
       5 -> R.drawable.dice_5
       else->R.drawable.dice_6
   })

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = imageResource, contentDescription = result.toString())
        Spacer(modifier=Modifier.height(16.dp))
        Button(onClick = { result=(1..6).random() }) {
            Text(text = stringResource(id = R.string.roll), fontFamily = FontFamily(Typeface.MONOSPACE))
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Unit2Theme {
        DiceWithButtonAndImage(
            )
    }
}