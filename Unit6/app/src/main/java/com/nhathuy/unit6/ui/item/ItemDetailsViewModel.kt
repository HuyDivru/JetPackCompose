package com.nhathuy.unit6.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhathuy.unit6.data.ItemsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*
    viewmodel to retrieve, update and delete an item from the [ItemsRepository] data's source
 */
class ItemDetailsViewModel(
    savedStateHandel:SavedStateHandle,
    private val itemsRepository: ItemsRepository,
) :ViewModel(){
    private val itemId:Int = checkNotNull(savedStateHandel[ItemDetailsDestination.itemIdArg])

    //
    val uiState:StateFlow<ItemDetailsUiState> = itemsRepository.getItemStream(itemId)
        .filterNotNull()
        .map {
            ItemDetailsUiState(outOfStock = it.quantity<=0, itemDetails = it.toItemDetails())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    fun reduceQuantityByOne(){
        viewModelScope.launch {
            val currentItem= uiState.value.itemDetails.toItem()
            if(currentItem.quantity>0){
                itemsRepository.updateItem(currentItem.copy(quantity = currentItem.quantity-1))
            }
        }
    }


    suspend fun deleteItem(){
        itemsRepository.deleteItem(uiState.value.itemDetails.toItem())
    }



    companion object{
        private const val TIMEOUT_MILLIS=5_000L
    }
}

// ui state for itemdetails screen
data class ItemDetailsUiState(
    val outOfStock:Boolean=true,
    val itemDetails:ItemDetails= ItemDetails()
)