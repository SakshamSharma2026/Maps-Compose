package com.saksham.jetpack.mapscompose.domain.repository

import com.saksham.jetpack.mapscompose.domain.model.MapMarker
import kotlinx.coroutines.flow.Flow

interface MapMarkerRepository {

    suspend fun insertMapsMarker(mapMarker: MapMarker)

    suspend fun deleteMapsMarker(mapMarker: MapMarker)


    fun getMapsMarker(): Flow<List<MapMarker>>

}