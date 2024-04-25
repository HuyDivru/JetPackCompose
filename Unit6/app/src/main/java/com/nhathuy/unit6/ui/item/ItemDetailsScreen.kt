package com.nhathuy.unit6.ui.item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhathuy.unit6.InventoryTopAppBar
import com.nhathuy.unit6.R
import com.nhathuy.unit6.data.Item
import com.nhathuy.unit6.ui.AppViewModelProvider
import com.nhathuy.unit6.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ItemDetailsDestination: NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    navigateToEditItem : (Int) -> Unit,
    navigateBack: () ->Unit,
    modifier: Modifier =Modifier,
    viewModel: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            InventoryTopAppBar(title = stringResource(id =ItemDetailsDestination.titleRes
            ), canNavigateBack = true, navigateUp = navigateBack)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToEditItem(uiState.value.itemDetails.id) },
            shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(end= WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(
                    LocalLayoutDirection.current))) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(id = R.string.edit_item_title) )
            }
        }, modifier = modifier
    ) {
        innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState=uiState.value,
            onSellItem= {viewModel.reduceQuantityByOne()},
            onDelete ={
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            modifier= Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun ItemDetailsBody(itemDetailsUiState: ItemDetailsUiState,
                    onSellItem: () -> Unit, onDelete: () -> Unit, modifier: Modifier=Modifier) {
   Column(modifier=modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))) {

       var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }


       ItemDetail(item = itemDetailsUiState.itemDetails.toItem(), modifier = Modifier.fillMaxWidth())
       Button(onClick = onSellItem,
       modifier = Modifier.fillMaxWidth(),
       shape = MaterialTheme.shapes.small,
       enabled = !itemDetailsUiState.outOfStock) {
            Text(text = stringResource(id = R.string.sell))
       }

       OutlinedButton(onClick = { deleteConfirmationRequired = true },
       shape = MaterialTheme.shapes.small,
       modifier = Modifier.fillMaxWidth()) {
           Text(text = stringResource(id = R.string.delete))
       }
       if(deleteConfirmationRequired){
           DeleteConfirmationDialog(
               onDeleteConfirm = {
                   deleteConfirmationRequired = false
                   onDelete()
               },
               onDeleteCancel= {deleteConfirmationRequired=false},
               modifier=Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
           )
       }
  }
}

@Composable
fun DeleteConfirmationDialog(onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier) {

}

@Composable
fun ItemDetail(item: Item, modifier: Modifier=Modifier) {
    Card(modifier=modifier, colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )) {
        Column(modifier= Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))) {
            ItemDetailRow(
                labelResId=R.string.item,
                itemDetail=item.name,
                modifier=Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun ItemDetailRow(labelResId: Int, itemDetail: String, modifier: Modifier=Modifier) {
    Row(modifier = modifier) {
        Text(text = stringResource(id = labelResId))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}


