package com.nhathuy.amphibians.data

import com.nhathuy.amphibians.model.Amphibians
import com.nhathuy.amphibians.network.AmphibiansApiServer

interface AmphibiansRepository {
    suspend fun getAmphibianPhotos(): List<Amphibians>
}
class NetworkAmphibiansRepository(private val apiServer: AmphibiansApiServer):AmphibiansRepository{
    override suspend fun getAmphibianPhotos():List<Amphibians> = apiServer.getAmphibians()
}