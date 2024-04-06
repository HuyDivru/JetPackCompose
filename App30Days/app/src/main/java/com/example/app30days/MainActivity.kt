package com.example.app30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app30days.module.Days
import com.example.app30days.module.listDay
import com.example.app30days.ui.theme.App30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App30DaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Day30Bro()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Day30Bro(){
    Scaffold(topBar = {DayTopAppBar()}) {
        it-> LazyColumn(contentPadding = it){
            items(listDay){
                DayItem(day =it, modifier =Modifier.padding(dimensionResource(id = R.dimen.day_padding_small)))
            }
    }
    }
}

@Composable
fun DayItem(day: Days, modifier: Modifier) {
    var click by remember {
        mutableStateOf(false)
    }

    Card(modifier = modifier) {
        Column(modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.day_padding_small)
            )) {
            Text(text = stringResource(id = day.nameDay), style = MaterialTheme.typography.displayMedium, modifier = Modifier.padding(
               start=dimensionResource(id = R.dimen.day_padding_small)))
            Text(text = stringResource(id = day.titleDay), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(
               start= dimensionResource(id = R.dimen.day_padding_small)))
            Box(modifier = modifier.clickable { click =!click }){
                Image(painter = painterResource(id = day.imageDay), contentDescription =null,modifier= Modifier
                    .fillMaxWidth()
                    .height(150.dp), contentScale = ContentScale.Crop)
            }
            if (click){
                Text(text = stringResource(id = day.descriptionDay), style = MaterialTheme.typography.bodyLarge, modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.day_padding_small)
                    ))
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayTopAppBar(modifier: Modifier=Modifier) {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.day6), contentDescription = null, modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.day_image)
                )
                .clip(CircleShape)
                .padding(dimensionResource(id = R.dimen.day_padding_medium)), contentScale = ContentScale.Crop)

            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge)
        }
    }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App30DaysTheme(darkTheme = false) {
        Day30Bro()
    }
}
@Preview
@Composable
fun DarkTheme(){
    App30DaysTheme(darkTheme = true) {
        Day30Bro()
    }
}