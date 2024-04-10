package com.saksham.jetpack.mapscompose.domain.model

data class MapMarker(
    val lat: Double = 0.0, val lng: Double = 0.0,
    val name: String? = null,
    val age: String? = null,
    val relation: String? = null,
    val address: String? = null,
    val id: Int? = null
)