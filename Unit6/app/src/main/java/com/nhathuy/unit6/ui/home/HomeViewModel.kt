package com.nhathuy.unit6.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhathuy.unit6.data.Item
import com.nhathuy.unit6.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel (itemsRepository: ItemsRepository):ViewModel(){
    val homeUiState:StateFlow<HomeUiState> =
        itemsRepository.getAllItemStream()
            .map {
                HomeUiState(it)
            }
            .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
            )

    companion object{
        private const val TIMEOUT_MILLIS=5_00L
    }
}
data class HomeUiState(val itemList:List<Item> = listOf())