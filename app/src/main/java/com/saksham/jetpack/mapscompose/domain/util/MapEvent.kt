package com.saksham.jetpack.mapscompose.domain.util

import com.google.android.gms.maps.model.LatLng
import com.saksham.jetpack.mapscompose.domain.model.MapMarker

sealed class MapEvent {
    data class ToggleDeleteMarkerDialog(val mapMarker: MapMarker? = null) : MapEvent()
    data object ToggleMarkerInformationDialog : MapEvent()
    data class OnMapSave(val mapMarker: MapMarker) : MapEvent()

    data class OnMapClick(val latLng: LatLng) : MapEvent()
    data class OnInfoWindowClick(val mapMarker: MapMarker) : MapEvent()

}