package com.example.unit1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.unit1.ui.theme.Unit1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingImage(message = getString(R.string.happy_birthday_text), from = getString(
                                            R.string.singature_text), context = this@MainActivity)
                }
            }
        }
    }
}
@Composable
fun GreetingImage(message:String,from:String,context:Context){
    val image= painterResource(id = R.drawable.background)
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = image, contentDescription = null, alpha = 0.5F, contentScale = ContentScale.Crop)
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
        Button(
            onClick = { changeActivity(context) },modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(6.dp))
        {
            Text(text = "Go to Bussiness Card")
        }
    }
}

fun changeActivity(context:Context)  {
    val intent=Intent(context,BusinessCardActivity::class.java);
    context.startActivity(intent);
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // Create a column so that texts don't overlap
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(end = 16.dp)
                .align(alignment = Alignment.End)

        )
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    Unit1Theme {
        GreetingImage(stringResource(R.string.happy_birthday_text),stringResource(R.string.singature_text), context = LocalContext.current)
    }
}