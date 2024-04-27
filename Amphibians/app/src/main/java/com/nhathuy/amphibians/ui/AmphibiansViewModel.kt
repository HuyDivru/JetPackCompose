package com.nhathuy.amphibians.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nhathuy.amphibians.AmphibiansApplication
import com.nhathuy.amphibians.data.AmphibiansRepository
import com.nhathuy.amphibians.model.Amphibians
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianState{
    data class Success(val photos: List<Amphibians>):AmphibianState
    object Error:AmphibianState
    object Loading:AmphibianState
}

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) :ViewModel() {

    var amphibiansUiState : AmphibianState by mutableStateOf(AmphibianState.Loading)
        private set

    init{
        getAmphibianPhotos()
    }

    fun getAmphibianPhotos() {
        viewModelScope.launch {
            amphibiansUiState=AmphibianState.Loading
            amphibiansUiState = try {
                AmphibianState.Success(amphibiansRepository.getAmphibianPhotos())
            }
            catch (e:IOException){
                AmphibianState.Error
            }
            catch (e:HttpException){
                AmphibianState.Error
            }
        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}