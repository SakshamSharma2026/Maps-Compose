package com.saksham.jetpack.mapscompose.data.model

import com.saksham.jetpack.mapscompose.data.data_source.MapMarkerEntity
import com.saksham.jetpack.mapscompose.domain.model.MapMarker

fun MapMarkerEntity.toMapsMarker(): MapMarker {
    return MapMarker(lat, lng, name, age, relation, address, id)
}


fun MapMarker.toMapsMarkerEntity(): MapMarkerEntity {
    return MapMarkerEntity(lat, lng, name, age, address, relation, id)
}

