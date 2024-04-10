package com.saksham.jetpack.mapscompose.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MapMarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapsMarker(marker: MapMarkerEntity)

    @Delete
    suspend fun deleteMapsMarker(marker: MapMarkerEntity)

    @Query("SELECT * FROM mapmarkerentity")
    fun getMapsMarker(): Flow<List<MapMarkerEntity>>
}