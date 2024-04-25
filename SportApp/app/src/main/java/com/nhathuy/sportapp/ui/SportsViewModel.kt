package com.nhathuy.sportapp.ui

import androidx.lifecycle.ViewModel
import com.nhathuy.sportapp.data.LocalSportsDataProvider
import com.nhathuy.sportapp.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SportsViewModel : ViewModel() {
    private val _uiState= MutableStateFlow(
        SportsUiState(
            sportsList = LocalSportsDataProvider.getSportData(),
            currentSport = LocalSportsDataProvider.getSportData().getOrElse(0){
                LocalSportsDataProvider.defaultSport
            }
        )
    )
    val uiState: StateFlow<SportsUiState> = _uiState

    fun updateCurrentSport(selectedSport:Sport){
        _uiState.update {
            it.copy(currentSport = selectedSport)
        }
    }

    fun navigateToListPage(){
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }
    fun navigateToDetailPage(){
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class SportsUiState(
    val sportsList : List<Sport> = emptyList(),
    val currentSport: Sport = LocalSportsDataProvider.defaultSport
    , val isShowingListPage : Boolean = true
)