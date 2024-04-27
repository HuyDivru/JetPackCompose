package com.nhathuy.amphibians

import android.app.Application
import com.nhathuy.amphibians.data.AppContainer
import com.nhathuy.amphibians.data.DefaultAppContainer

class AmphibiansApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=DefaultAppContainer()
    }
}