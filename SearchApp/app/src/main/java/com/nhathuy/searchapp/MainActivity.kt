package com.nhathuy.searchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhathuy.searchapp.ui.theme.SearchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier=Modifier){
    TextField(value = "",
        onValueChange ={},
    leadingIcon ={
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    colors = TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedContainerColor = MaterialTheme.colorScheme.surface
    ),
        placeholder = {
                      Text(text = stringResource(id = R.string.placeholder_search))
        } ,
    modifier= modifier
        .fillMaxWidth()
        .heightIn(min = 25.dp))
}
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,modifier: Modifier=Modifier){
    Column(modifier=modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(drawable),
            contentScale = ContentScale.Crop
            , contentDescription = null,modifier= Modifier
                .size(88.dp)
                .clip(
                    CircleShape
                ))
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
fun FavoriteCollectionCard(@DrawableRes drawable:Int,@StringRes text:Int,modifier: Modifier=Modifier){
    Surface(shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)) {
           Image(painter = painterResource(drawable), contentDescription = null,
           contentScale = ContentScale.Crop, modifier = Modifier.size(80.dp))
            Text(text = stringResource(text), style = MaterialTheme.typography.titleMedium,modifier=Modifier.padding(horizontal = 16.dp))
        }
    }
}
@Composable
fun AlignYourBodyRow(modifier: Modifier=Modifier){
    LazyRow(modifier=modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)
        , contentPadding = PaddingValues(horizontal = 16.dp)){
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable,item.text)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SearchAppTheme {
       SearchBar()
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignPreview(){
    SearchAppTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview(){
    SearchAppTheme {
        AlignYourBodyElement(
            text = R.string.ab1_inversions,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}
