package com.example.woofappinunit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.PlayArrow
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
import com.example.woofappinunit3.module.Dog
import com.example.woofappinunit3.module.listDog
import com.example.woofappinunit3.ui.theme.WoofAppInUnit3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofAppInUnit3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofApp() {
    Scaffold(topBar = { WoofTopAppBar() }) { it ->
        LazyColumn(contentPadding = it) {
            items(listDog) {
                DogItem(
                    dog = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}
@Composable
fun DogItem(dog: Dog, modifier: Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                DogHobby(
                    dog.hobbies, modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(
                            id = R.dimen.padding_medium
                        ),
                        top = dimensionResource(id = R.dimen.padding_small),
                        bottom = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }
        }
    }
}
    @Composable
 fun DogHobby(@StringRes hobbies: Int, modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = R.string.about),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(hobbies),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    @Composable
 fun DogItemButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
        IconButton(onClick = onClick, modifier = modifier) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.PlayArrow,
                contentDescription = stringResource(id = R.string.expand_button_content_description),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }

    @Composable
fun DogInformation(@StringRes name: Int, age: Int, modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = name),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(
                    top = dimensionResource(
                        id = R.dimen.padding_small
                    )
                )
            )
            Text(
                text = stringResource(R.string.years_old, age),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    @Composable
fun DogIcon(@DrawableRes imageResourceId: Int, modifier: Modifier = Modifier) {
        Image(
            painter = painterResource(id = imageResourceId), contentDescription = null,
            modifier = modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .padding(
                    dimensionResource(
                        id = R.dimen.padding_small
                    )
                )
                .clip(MaterialTheme.shapes.small), contentScale = ContentScale.Crop
        )   
    }

    @Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(
                            dimensionResource(id = R.dimen.image_size)
                        )
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }, modifier = modifier)

}
@Preview
@Composable
fun WoofDarkPreview() {
    WoofAppInUnit3Theme (darkTheme = true){
        WoofApp()
    }
}
@Preview
@Composable
fun WoofPreview() {
    WoofAppInUnit3Theme (darkTheme = false){
        WoofApp()
    }
}