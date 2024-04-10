package com.saksham.jetpack.mapscompose.data.data_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MapMarkerEntity(
    val lat: Double,
    val lng: Double,
    val name: String? = null,
    val age: String? = null,
    val address: String? = null,
    val relation: String? = null,
    @PrimaryKey val id: Int? = null
)