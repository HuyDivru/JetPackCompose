package com.example.unit2.artspaceart

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit2.R
import com.example.unit2.artspaceart.ui.theme.Unit2Theme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtMain()
                }
            }
        }
    }
}
@Composable
fun ArtMain(){
    var result by remember{
        mutableStateOf(1)
    }
    when(result){
        1-> ArtContent(imageArt = R.drawable.te, titleArt = "Không Bỏ Cuộc", nameArt = "Huy", modifier = Modifier)
        2-> ArtContent(imageArt = R.drawable.pic_1, titleArt = "Chiến Thắng Vinh Quang", nameArt = "Huy Nguyễn", modifier = Modifier)
        3-> ArtContent(imageArt = R.drawable.pic_2, titleArt = "Đêm Tối Tại Thụy Sĩ", nameArt = "Alex Fomat", modifier = Modifier)
        4 -> ArtContent(imageArt = R.drawable.pic_3, titleArt = "Chiến tàu du ngoại Ô thuy sĩ", nameArt = "Omg", modifier = Modifier)
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            0.dp,
            alignment = Alignment.CenterHorizontally
        ), verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(bottom = 30.dp)
    ) {
        Button(onClick = { if(result==1){result==4} else {result--} }, modifier = Modifier.padding(start = 50.dp, end = 30.dp)) {
            Text(text = "Back")
        }
        Button(onClick = { if(result==4){result==1} else {result++} }, modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ArtContent(imageArt: Int, titleArt: String, nameArt: String,modifier: Modifier=Modifier) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageArt), contentDescription =null,modifier= Modifier
            .padding(20.dp)
            .border(
                border = BorderStroke(2.dp, Color.Gray), shape = RectangleShape
            )
            .height(350.dp)
            .width(300.dp)
            .shadow(elevation = 4.dp, shape = RectangleShape)
            .padding(18.dp))
        Card(modifier = Modifier.padding(horizontal = 20.dp, vertical = 100.dp)) {
            Column(modifier = Modifier.fillMaxWidth().padding(start = 4.dp)) {
                Text(text = titleArt, fontSize = 30.sp, fontWeight = FontWeight.Medium)
                Text(text = nameArt, fontSize = 23.sp, fontWeight = FontWeight.Thin)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    Unit2Theme {
        ArtMain()
    }
}