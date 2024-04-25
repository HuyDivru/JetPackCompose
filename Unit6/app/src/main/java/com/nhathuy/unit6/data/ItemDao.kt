package com.nhathuy.unit6.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:Item)
    @Update
    suspend fun update(item:Item)
    @Delete
    suspend fun delete(item: Item)

    @Query("select * from items where id= :id")
    fun getItem(id:Int) : Flow<Item>
    @Query("select * from items order by name asc")
    fun getAllItems():Flow<List<Item>>
}