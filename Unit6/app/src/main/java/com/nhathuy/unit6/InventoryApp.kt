package com.nhathuy.unit6

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title:String,
    canNavigateBack:Boolean,
    modifier: Modifier=Modifier,
    scrollBehavior: TopAppBarScrollBehavior ? =null,
    navigateUp : () -> Unit,
){
    CenterAlignedTopAppBar(title = { Text(text = title)},
    modifier = modifier,scrollBehavior=scrollBehavior,
    navigationIcon = {
       if (canNavigateBack){
           IconButton(onClick = navigateUp) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back_btn))
           }
       }
    })
}