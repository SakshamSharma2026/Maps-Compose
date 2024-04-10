package com.saksham.jetpack.mapscompose.domain.util

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.saksham.jetpack.mapscompose.domain.model.MapMarker

data class MapState(
    val properties: MapProperties = MapProperties(
        mapType = MapType.HYBRID,
        isBuildingEnabled = true,
        isIndoorEnabled = true,
        isTrafficEnabled = true
    ),
    val isMarkerInformationDialogVisible: Boolean = false,
    val isDeleteDialogVisible: Boolean = false,
    var latLng: LatLng? = null,
    var mapMarkerSaved: MapMarker = MapMarker(),
    val mapMarker: List<MapMarker> = emptyList()
)