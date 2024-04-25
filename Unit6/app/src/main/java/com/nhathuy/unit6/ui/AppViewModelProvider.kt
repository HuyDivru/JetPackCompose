package com.nhathuy.unit6.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nhathuy.unit6.InventoryApplication
import com.nhathuy.unit6.data.InventoryDatabase
import com.nhathuy.unit6.ui.item.ItemEntryViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory= viewModelFactory {
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication() : InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)