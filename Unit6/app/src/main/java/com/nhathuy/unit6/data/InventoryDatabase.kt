package com.nhathuy.unit6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase(){
    abstract fun itemDao():ItemDao
    //which allows access to the methods to create or get the database and uses the class name as the qualifier
    companion object{
        // It means that changes made by one thread to Instance are immediately visible to all other thread
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context:Context) :InventoryDatabase{
            // if the instance is not null, return it,otherwise create a new database instance
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database").fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }
}