package com.nhathuy.marsphotos.network

import com.nhathuy.marsphotos.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}