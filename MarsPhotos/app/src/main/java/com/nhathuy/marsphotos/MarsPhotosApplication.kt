package com.nhathuy.marsphotos

import android.app.Application
import com.nhathuy.marsphotos.data.AppContainer
import com.nhathuy.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication: Application() {

    /** Phiên bản AppContainer được các lớp còn lại sử dụng để lấy phần phụ thuộc */
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container :AppContainer
    override fun onCreate() {
        super.onCreate()
        container=DefaultAppContainer()
    }
}