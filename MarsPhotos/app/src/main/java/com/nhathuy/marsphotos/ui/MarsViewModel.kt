package com.nhathuy.marsphotos.ui

import android.text.Editable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.nhathuy.marsphotos.MarsPhotosApplication
import com.nhathuy.marsphotos.data.MarsPhotosRepository
import com.nhathuy.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//const val APPLICATION_KEY = "android.app.Application"
//mars ui state
sealed interface MarsUiState{
    data class Success(val photos:List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading: MarsUiState
}

class MarsViewModel(private val marsPhotosRepository:MarsPhotosRepository):ViewModel() {

    // declare a variable named marsUistate with marsuistate
    // use mutablestateof() delegate to manage the state of this variable
    var marsUiState : MarsUiState by mutableStateOf(MarsUiState.Loading)
        //specify that the setter for this property is private
        private set

    /*
    call getMarsPhotos() on init so we can display status immediately
     */
    init {
        getMarsPhotos()
    }
    /*
    get mars photos information from the mars api retrofit service and update the marsphoto list mutablelist
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState=MarsUiState.Loading
            marsUiState=try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            }
            catch (e:IOException){
                MarsUiState.Error
            }
            catch (e:HttpException){
                MarsUiState.Error
            }
        }
    }
    /*
    factory for marsviewmodel that takes marsphotosrespository as a dependency
     */
    // được sử dụng để tạo ra một đối tượng đặc biệt được gắn liền lớp mà không cần tạo ra một thể hiện của lớp đó
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }

}