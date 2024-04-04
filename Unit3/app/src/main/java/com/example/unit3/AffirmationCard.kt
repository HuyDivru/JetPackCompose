package com.example.unit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit3.data.DataSource
import com.example.unit3.model.Affirmation
import com.example.unit3.ui.theme.Unit3Theme

class AffirmationCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationList(affirmationList = DataSource().loadAffirmations())
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffirmationCards(affirmation: Affirmation,modifier: Modifier = Modifier){
    Card(modifier=modifier){
        Column {
            Image(painter = painterResource(affirmation.imageResourceId), contentDescription = stringResource(
                id = affirmation.stringResouceId
            ), modifier = Modifier
                .fillMaxWidth()
                .height(194.dp), contentScale = ContentScale.Crop)
            
            Text(
                text = LocalContext.current.getString(affirmation.stringResouceId),
                modifier = Modifier.padding(10.dp),
                style=MaterialTheme.typography.headlineSmall
            )
        }
    }
}
@Composable
fun AffirmationList(affirmationList:List<Affirmation>,modifier: Modifier=Modifier){
    LazyColumn(modifier = modifier){
        items(affirmationList){ affirmation ->
            AffirmationCards(affirmation = affirmation,modifier=Modifier.padding(8.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Unit3Theme {
        AffirmationList(affirmationList = DataSource().loadAffirmations())
    }
}