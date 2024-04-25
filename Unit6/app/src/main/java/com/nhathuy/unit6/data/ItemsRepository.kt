package com.nhathuy.unit6.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository {


    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemStream() : Flow<List<Item>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id:Int):Flow<Item?>
    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Item)
    /**
     * Update item in the data source
     */
    suspend fun updateItem(item:Item)
    /**
     * Delete item in the data source
     */
    suspend fun deleteItem(item:Item)

}