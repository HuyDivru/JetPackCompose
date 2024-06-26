package com.nhathuy.unit6.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemStream(): Flow<List<Item>> =itemDao.getAllItems()
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)
    override suspend fun insertItem(item: Item) = itemDao.insert(item)
    override suspend fun updateItem(item: Item) =itemDao.update(item)
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)


}