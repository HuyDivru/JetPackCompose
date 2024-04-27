@file:OptIn(ExperimentalMaterial3Api::class)
package com.nhathuy.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhathuy.amphibians.R

@Composable
fun AmphibiansApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        AmphibiansTopBar(scrollBehavior= scrollBehavior)
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
            HomeScreen(amphibiansUiState=amphibiansViewModel.amphibiansUiState,
            retryAction=amphibiansViewModel::getAmphibianPhotos,contendPadding=it)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopBar(scrollBehavior: TopAppBarScrollBehavior,modifier: Modifier=Modifier) {
    TopAppBar(scrollBehavior = scrollBehavior,
        title = {
                Text(text = stringResource(R.string.name),
                style = MaterialTheme.typography.headlineSmall)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ), modifier = modifier)
}
