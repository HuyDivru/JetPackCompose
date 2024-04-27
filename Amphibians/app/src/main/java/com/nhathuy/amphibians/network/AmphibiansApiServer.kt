package com.nhathuy.amphibians.network

import com.nhathuy.amphibians.model.Amphibians
import retrofit2.http.GET

interface AmphibiansApiServer {
    @GET("amphibians")
    suspend fun getAmphibians() : List<Amphibians>
}