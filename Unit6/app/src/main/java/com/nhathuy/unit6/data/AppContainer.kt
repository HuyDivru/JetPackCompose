package com.nhathuy.unit6.data

import android.content.Context

/*
app container for dependecy injection
 */
interface AppContainer {
    val itemsRepository:ItemsRepository
}
/*
appcontainer implementation that provides instance of [offlineitemsrepository]
 */
class AppDataContainer(private val context:Context) : AppContainer{
    override val itemsRepository: ItemsRepository
    by  lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
