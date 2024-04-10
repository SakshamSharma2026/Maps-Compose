package com.saksham.jetpack.mapscompose.presentation.map_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.saksham.jetpack.mapscompose.domain.model.MapMarker
import com.saksham.jetpack.mapscompose.domain.repository.MapMarkerRepository
import com.saksham.jetpack.mapscompose.domain.util.MapEvent
import com.saksham.jetpack.mapscompose.domain.util.MapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repository: MapMarkerRepository) :
    ViewModel() {

    private val _state = mutableStateOf(MapState())
    var state: State<MapState> = _state

    init {
        viewModelScope.launch {
            repository.getMapsMarker().collectLatest { marker ->
                _state.value = state.value.copy(
                    mapMarker = marker
                )
            }
        }
    }


    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnInfoWindowClick -> {
                viewModelScope.launch {
                    repository.deleteMapsMarker(
                        event.mapMarker
                    )
                }
            }

            is MapEvent.OnMapClick -> {
                _state.value = state.value.copy(
                    latLng = LatLng(event.latLng.latitude, event.latLng.longitude)
                )

            }

            is MapEvent.ToggleDeleteMarkerDialog -> {
                _state.value =
                    state.value.copy(isDeleteDialogVisible = !state.value.isDeleteDialogVisible)

                if (state.value.isDeleteDialogVisible)
                    event.mapMarker?.let {
                        _state.value = state.value.copy(
                            mapMarkerSaved = it
                        )
                    }
            }

            MapEvent.ToggleMarkerInformationDialog -> {
                _state.value =
                    state.value.copy(isMarkerInformationDialogVisible = !state.value.isMarkerInformationDialogVisible)
            }

            is MapEvent.OnMapSave -> {
                viewModelScope.launch {
                    repository.insertMapsMarker(
                        MapMarker(
                            lat = event.mapMarker.lat,
                            lng = event.mapMarker.lng,
                            name = event.mapMarker.name,
                            age = event.mapMarker.age,
                            relation = event.mapMarker.relation,
                            address = event.mapMarker.address,
                        )
                    )
                    _state.value = state.value.copy(
                        latLng = LatLng(0.0, 0.0)
                    )
                }
            }
        }
    }
}