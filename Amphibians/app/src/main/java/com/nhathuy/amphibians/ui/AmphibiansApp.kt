@file:OptIn(ExperimentalMaterial3Api::class)
package com.nhathuy.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll


@Composable
fun AmphibiansApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        AmphibiansTopBar(scrollBehavior= scrollBehavior)
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@Composable
fun AmphibiansTopBar(scrollBehavior: TopAppBarScrollBehavior,modifier: Modifier=Modifier) {

}
