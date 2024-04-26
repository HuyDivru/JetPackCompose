package com.nhathuy.marsphotos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nhathuy.marsphotos.R
import com.nhathuy.marsphotos.model.MarsPhoto
import com.nhathuy.marsphotos.ui.theme.MarsPhotosTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsPhotoApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {MarsTopAppBar(scrollBehavior=scrollBehavior)}) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val marsViewModel: MarsViewModel= viewModel(factory = MarsViewModel.Factory)
            HomeScreen(marsUiState = marsViewModel.marsUiState, retryAction =  marsViewModel::getMarsPhotos,
            contentPadding = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior,modifier: Modifier=Modifier) {
    CenterAlignedTopAppBar(scrollBehavior=scrollBehavior, title = {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineSmall)
    }, modifier = modifier)
}


@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction: () ->Unit,
    modifier: Modifier = Modifier,
    contentPadding : PaddingValues = PaddingValues(0.dp)
){
    when(marsUiState){
        is MarsUiState.Loading ->LoadingScreen(modifier=modifier.fillMaxSize())
        is MarsUiState.Success -> PhotoGridScreen(
            marsUiState.photos,contentPadding = contentPadding,modifier=modifier.fillMaxWidth()
        )
        is MarsUiState.Error -> ErrorScreen(retryAction,modifier=modifier.fillMaxSize())
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier=Modifier) {
    Column(modifier = modifier
        , verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_error_24), contentDescription ="")
        Text(text = stringResource(R.string.loading_falied),modifier=Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.loading_failed))
        }
    }
}

@Composable
fun PhotoGridScreen(photos: List<MarsPhoto>, contentPadding: PaddingValues= PaddingValues(0.dp), modifier: Modifier=Modifier) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), contentPadding = contentPadding, modifier = modifier.padding(horizontal = 4.dp)){
        items(items = photos , key = {photo -> photo.id }) {
            photo ->
            MarsPhotoCard(photo,modifier= modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f))
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier=Modifier) {
    Card(modifier = modifier
    , shape = MaterialTheme.shapes.medium,
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(photo.imageMars).crossfade(true).build(), contentDescription = stringResource(
                    R.string.mars_photo),
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading),
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier=Modifier) {
    Image(painter = painterResource(id = R.drawable.loading)
        , contentDescription = stringResource(id = R.string.loading)
    ,modifier=modifier.size(200.dp))
}
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview(){
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoGridScreenPreview(){
    MarsPhotosTheme {
        val mockDate = List(10) {
            MarsPhoto("$it","")}
            PhotoGridScreen(photos = mockDate)
        }
    }
