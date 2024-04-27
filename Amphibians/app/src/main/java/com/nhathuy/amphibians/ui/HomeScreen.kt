package com.nhathuy.amphibians.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nhathuy.amphibians.R
import com.nhathuy.amphibians.model.Amphibians

@Composable
fun HomeScreen(amphibiansUiState: AmphibianState, retryAction: () -> Unit, contendPadding: PaddingValues= PaddingValues(0.dp),modifier: Modifier=Modifier) {
    when(amphibiansUiState){
        is AmphibianState.Loading ->LoadingScreen(modifier=modifier.fillMaxSize())
        is AmphibianState.Error ->ErrorScreen(retryAction,modifier=modifier.fillMaxSize())
        is AmphibianState.Success -> AmphibiansPhoto(amphibiansUiState.photos,contentPadding=contendPadding,modifier=modifier.fillMaxSize())
    }
}

@Composable
fun AmphibiansPhoto(photos: List<Amphibians>, contentPadding: PaddingValues= PaddingValues(0.dp), modifier: Modifier=Modifier) {
    LazyColumn(contentPadding=contentPadding, modifier = modifier.padding(horizontal = 4.dp)){
        items(items = photos, key = {photo -> photo.name}){
            photo ->
            AmphibiansPhotoCard(photo,modifier= modifier
                .padding(4.dp)
                .fillMaxWidth())
        }
    }
}

@Composable
fun AmphibiansPhotoCard(photo: Amphibians, modifier: Modifier=Modifier) {
    Card(modifier = modifier, shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        Row {
           Text(text = photo.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(text = photo.type, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgSrc).crossfade(true).build(),
            contentDescription = stringResource(R.string.photo),
            error = painterResource(id = R.drawable.error),
            placeholder = painterResource(id = R.drawable.loading),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = photo.description, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Thin)
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier=Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.error), contentDescription = "")
        Text(text = stringResource(R.string.loading_failed),modifier=Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier=Modifier) {
    Image(painter = painterResource(id = R.drawable.loading), contentDescription = stringResource(R.string.loading_image),modifier=modifier.size(200.dp))
}
