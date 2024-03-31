package com.example.unit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit2.ui.theme.Unit2Theme

class LemonadeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF3CEBD9)
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(){

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Lemonade", fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF3CEBD9),
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }){
        innerPadding -> Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
        color = MaterialTheme.colorScheme.background
        )
    {
        when(currentStep){
            1 ->{
                LemonTextAndImage(textLabelResourceId=R.string.lemon_select,
                    drawableResourceId=R.drawable.lemon_tree,
                    contentDescriptionResourceId=R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep=2
                        squeezeCount=(2..4).random()
                    }
                )
            }
            2->{
                LemonTextAndImage(textLabelResourceId=R.string.lemon_select,
                    drawableResourceId=R.drawable.lemon_tree,
                    contentDescriptionResourceId=R.string.lemon_tree_content_description,
                    onImageClick = {
                        squeezeCount--
                        if(squeezeCount==0){
                            currentStep=3
                        }
                    }
                )
            }
            3->{
                LemonTextAndImage(textLabelResourceId=R.string.lemon_select,
                    drawableResourceId=R.drawable.lemon_tree,
                    contentDescriptionResourceId=R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep=4
                    }
                )
            }
            4->{
                LemonTextAndImage(textLabelResourceId=R.string.lemon_select,
                    drawableResourceId=R.drawable.lemon_tree,
                    contentDescriptionResourceId=R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep=1
                    }
                )
            }
        }

    }
    }

}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
            Button(onClick = { onImageClick },
            shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(painter = painterResource(id = drawableResourceId), contentDescription = stringResource(contentDescriptionResourceId
                ),modifier= Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .padding(32.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(id = textLabelResourceId), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Unit2Theme {
        LemonadeApp()
    }
}