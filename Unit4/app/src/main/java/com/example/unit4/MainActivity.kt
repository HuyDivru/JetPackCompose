package com.example.unit4

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.unit4.data.DataSource
import com.example.unit4.model.Dessert
import com.example.unit4.ui.theme.Unit4Theme

private const val TAG="MainActivity";

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"OnCreated Called")
        setContent {
            Unit4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DessertClickApp(desserts = DataSource.dessertList)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"OnRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy Called")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DessertClickApp(desserts:List<Dessert>){
    var revenue by rememberSaveable{ mutableStateOf(0) }

    var dessertSold by rememberSaveable{ mutableStateOf(0) }

    var currentDessertIndex by rememberSaveable{ mutableStateOf(0) }

    var currentDessertPrice by rememberSaveable  {
        mutableStateOf(desserts[currentDessertIndex].price)
    }

    var currentDessertImageId by rememberSaveable{
        mutableStateOf(desserts[currentDessertIndex].image)
    }
    Scaffold(topBar = {
        val intentContext= LocalContext.current
        val layoutDirection= LocalLayoutDirection.current
        DessertClickAppBar(
            onButtonClicked={
             shareSoldDessertsInformation(intentContext=intentContext,
                 dessertSold=dessertSold,
                 revenue=revenue)
            }
            ,modifier= Modifier
                .fillMaxWidth()
                .padding(
                    start = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection)
                )
                .background(MaterialTheme.colorScheme.primary))
    }) {
       contentPadding -> DessertClickerScreen(
        revenue = revenue,
        dessertSold = dessertSold,
        dessertImageId = currentDessertImageId,
        onDessertClicked = {

            // Update the revenue
            revenue += currentDessertPrice
            dessertSold++

            // Show the next dessert
            val dessertToShow = determineDessertToShow(desserts, dessertSold)
            currentDessertImageId = dessertToShow.image
            currentDessertPrice = dessertToShow.price
        }
        ,modifier=Modifier.padding(contentPadding)
       )
    }
}

fun determineDessertToShow(desserts: List<Dessert>, dessertSold: Int): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            break
        }
    }

    return dessertToShow
}

@Composable
fun DessertClickerScreen(revenue: Int, dessertSold: Int, dessertImageId: Int, onDessertClicked: () -> Unit, modifier: Modifier) {
       Box(modifier = modifier){
           Image(painter = painterResource(id = R.drawable.bakery_back), contentDescription = null, contentScale = ContentScale.Crop)
           
           Column {
               Box(modifier = Modifier
                   .weight(1f)
                   .fillMaxWidth()){
                   Image(painter = painterResource(id = dessertImageId), contentDescription =null, modifier = Modifier
                       .width(
                           dimensionResource(id = R.dimen.image_size)
                       )
                       .height(dimensionResource(id = R.dimen.image_size))
                       .align(Alignment.Center)
                       .clickable { onDessertClicked() }, contentScale = ContentScale.Crop )
               }
               TransactionInfo(revenue=revenue,
               dessertSold=dessertSold,
               modifier=Modifier.background(MaterialTheme.colorScheme.secondaryContainer))
           }
       } 
}

@Composable
fun TransactionInfo(revenue: Int, dessertSold: Int, modifier: Modifier) {
    Column(modifier = Modifier) {
        DessertSoldInfo(dessertSold=dessertSold,modifier= Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.padding_medium)
            ))
        RevenueInfo(revenue =dessertSold,modifier= Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.padding_medium)
            ))
    }
}

@Composable
fun RevenueInfo(revenue: Int, modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(id = R.string.total_revenue), style = MaterialTheme.typography.headlineLarge)
        Text(text = "$${revenue}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSecondaryContainer)
    }
}

@Composable
fun DessertSoldInfo(dessertSold: Int, modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(id = R.string.dessert_sold), style = MaterialTheme.typography.headlineLarge)
        Text(text = dessertSold.toString(), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSecondaryContainer)
    }
}

@Composable
fun DessertClickAppBar(onButtonClicked: () -> Unit, modifier: Modifier) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = R.string.app_name)
            ,modifier=Modifier
                .padding(start = dimensionResource(
            id = R.dimen.padding_medium
        ))
            , color = MaterialTheme.colorScheme.onPrimary
            , style = MaterialTheme.typography.titleLarge)
        IconButton(
            onClick = onButtonClicked
            , modifier = modifier.padding
                (end = dimensionResource(id = R.dimen.padding_medium))) {
            Icon(imageVector = Icons.Filled.Share
                , contentDescription = stringResource(id = R.string.share_text)
                , tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@SuppressLint("StringFormatInvalid")
fun shareSoldDessertsInformation(intentContext: Context, dessertSold: Int, revenue: Int) {
    val sendIntent=Intent().apply {
        action=Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,intentContext.getString(R.string.share_text,dessertSold,revenue))
        type="text/plain"
    }
    val shareIntent= Intent.createChooser(sendIntent,null)
    try {
        ContextCompat.startActivity(intentContext,shareIntent,null)
    }catch (e:ActivityNotFoundException){
        Toast.makeText(intentContext,intentContext.getString(R.string.sharing_not_available),Toast.LENGTH_LONG).show()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Unit4Theme {
        DessertClickApp(desserts = DataSource.dessertList)
    }
}