package com.saksham.jetpack.mapscompose.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MapMarkerEntity::class], version = 1)
abstract class MapMarkerDatabase : RoomDatabase() {
    abstract val dao: MapMarkerDao
}