package com.saksham.jetpack.mapscompose.data.repository

import com.saksham.jetpack.mapscompose.data.data_source.MapMarkerDao
import com.saksham.jetpack.mapscompose.data.model.toMapsMarker
import com.saksham.jetpack.mapscompose.data.model.toMapsMarkerEntity
import com.saksham.jetpack.mapscompose.domain.model.MapMarker
import com.saksham.jetpack.mapscompose.domain.repository.MapMarkerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MapMarkerRepositoryImpl(private val dao: MapMarkerDao) : MapMarkerRepository {
    override suspend fun insertMapsMarker(mapMarker: MapMarker) {
        dao.insertMapsMarker(mapMarker.toMapsMarkerEntity())
    }

    override suspend fun deleteMapsMarker(mapMarker: MapMarker) {
        dao.deleteMapsMarker(mapMarker.toMapsMarkerEntity())
    }

    override fun getMapsMarker(): Flow<List<MapMarker>> {
        return dao.getMapsMarker().map { maker ->
            maker.map { it.toMapsMarker() }
        }
    }
}