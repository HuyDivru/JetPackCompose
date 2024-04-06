package com.example.supperheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supperheroes.data.SuperHero
import com.example.supperheroes.data.listHero
import com.example.supperheroes.ui.theme.SupperHeroesTheme
import com.example.supperheroes.ui.theme.Typography
import kotlin.text.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupperHeroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroes()
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
fun SuperHeroes(){
    Scaffold(topBar = {HeroTopAppBar()}) {
        it -> LazyColumn(contentPadding = it){
            items(listHero){
                HeroItem(hero = it,modifier=Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier=Modifier) {
   CenterAlignedTopAppBar(title = {
       Row(verticalAlignment = Alignment.CenterVertically) {

           Image(painter = painterResource(id = R.drawable.hero7), contentDescription =null
        ,modifier = Modifier
                   .size(72.dp)
                   .padding(dimensionResource(id = R.dimen.padding_medium)))
          Text(text = stringResource(id = R.string.app_name),style = MaterialTheme.typography.displayLarge)
       }
   }) 
    
}

@Composable
fun HeroItem(hero: SuperHero, modifier: Modifier=Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(2.dp)) {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)) {
                HeroInformation(hero.nameHero,hero.descriptionHero,modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                HeroIcon(hero.imageHero)
            }

    }
}

@Composable
fun HeroIcon(imageHero: Int,modifier: Modifier=Modifier) {
    Box(modifier = Modifier
        .size(72.dp)
        .clip(RoundedCornerShape(8.dp))){
        Image(painter = painterResource(id = imageHero), contentDescription = null, alignment = Alignment.TopCenter, contentScale = ContentScale.FillWidth)
    }
}

@Composable
fun HeroInformation(nameHero: Int, descriptionHero: Int,modifier: Modifier=Modifier) {
    Column(modifier =modifier
        ){
            Text (text = stringResource(id = nameHero),
            style = MaterialTheme.typography.displaySmall,
            fontSize = 20.sp
        )
        Text(text = stringResource(id = descriptionHero),  style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SupperHeroesTheme (darkTheme = true){
        SuperHeroes()
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SupperHeroesTheme (darkTheme = false){
        SuperHeroes()
    }
}