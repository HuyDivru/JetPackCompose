package com.example.unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit1.ui.theme.Unit1Theme

class BusinessCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF03DAC5)
                ) {
                    GettingViewCenter()
                }
            }
        }
    }
}
@Composable
fun GettingViewCenter(){
    Column(horizontalAlignment=Alignment.CenterHorizontally
    ,verticalArrangement=Arrangement.Center) {
        val image= painterResource(id = R.drawable.android_logo)
        Image(painter = image, contentDescription = null,Modifier.fillMaxSize(0.25f))
        Text(text = stringResource(R.string.name), fontSize = 50.sp, color = Color.White)
        Text(text = stringResource(R.string.content), fontSize = 20.sp, fontFamily = FontFamily.Monospace, color = Color.Cyan, modifier =Modifier.padding(start = 10.dp, end = 10.dp))
        Spacer(modifier = Modifier.padding(bottom = 150.dp))
        ViewRow(icon = Icons.Rounded.Phone, text = "01653435175")
        ViewRow(icon = Icons.Rounded.Share, text = "@Android Dev")
        ViewRow(icon = Icons.Rounded.Email, text = "honhathuy098@gmail.com")
    }
}
@Composable
fun ViewRow(icon:ImageVector,text:String){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(16.dp)) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Green, modifier = Modifier.weight(1f))
        Text(text = text, color = Color.White, modifier = Modifier.weight(4f), fontFamily = FontFamily.SansSerif)
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF03DAC5)
    ) {
        Unit1Theme {
            GettingViewCenter()
        }
    }
}