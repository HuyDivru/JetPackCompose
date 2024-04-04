package com.example.unit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit3.data.DataSource
import com.example.unit3.model.Topic
import com.example.unit3.ui.theme.Unit3Theme

class GridGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        TopicGrid(topicList = DataSource().loadGridGame())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridGameCard(topic: Topic,modifier: Modifier=Modifier){
    Card(modifier = modifier) {
        Row{
            Box{
                Image(painter = painterResource(id = topic.imageDrawableId), contentDescription =null,
                modifier= Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.padding_big),
                        height = dimensionResource(
                            id = R.dimen.padding_big
                        )
                    )
                    .aspectRatio(1f), contentScale = ContentScale.Crop)
            }
            Column {
                Text(text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier =Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end= dimensionResource(id = R.dimen.padding_medium),
                        top= dimensionResource(id = R.dimen.padding_medium),
                        bottom= dimensionResource(id = R.dimen.padding_small)
                    ) )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription =null,modifier=Modifier.padding(start = dimensionResource(
                        id = R.dimen.padding_medium
                    )))
                    Text(text = topic.count.toString(),
                        style=MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start= dimensionResource(id = R.dimen.padding_medium)))
                }
            }
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicGrid(topicList : List<Topic>,modifier: Modifier=Modifier){
    LazyVerticalGrid(GridCells.Fixed(2),verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    , horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)), modifier = modifier){
        items(topicList){ topic ->
            GridGameCard(topic = topic, modifier = modifier)
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    Unit3Theme {
        TopicGrid(topicList = DataSource().loadGridGame())
    }
}