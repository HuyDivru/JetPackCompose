package com.nhathuy.unit6

import android.app.Application
import com.nhathuy.unit6.data.AppContainer
import com.nhathuy.unit6.data.AppDataContainer

class InventoryApplication :Application() {
    lateinit var container:AppContainer
    override fun onCreate() {
        super.onCreate()
        container=AppDataContainer(this)
    }
}