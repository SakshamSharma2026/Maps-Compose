package com.saksham.jetpack.mapscompose.presentation.map_screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.saksham.jetpack.mapscompose.domain.util.MapEvent
import com.saksham.jetpack.mapscompose.presentation.map_screen.components.CustomMarkerInfoWindow
import com.saksham.jetpack.mapscompose.presentation.map_screen.components.DeleteAlertDialog
import com.saksham.jetpack.mapscompose.presentation.map_screen.components.MarkerInformationDialog

@Composable
fun MapsScreen(
    context: Context,
    mapViewModel: MapViewModel = viewModel()
) {

    val state = mapViewModel.state.value
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }


    Scaffold { paddingValues ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            properties = state.properties,
            uiSettings = uiSettings,
            onMapClick = {
                mapViewModel.onEvent(MapEvent.OnMapClick(LatLng(it.latitude, it.longitude)))
            }
        ) {
            
            // For Unsaved Marker
            state.latLng?.let {
                Marker(
                    state = MarkerState(
                        LatLng(
                            it.latitude, it.longitude
                        )
                    ),
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    onClick = {
                        mapViewModel.onEvent(MapEvent.ToggleMarkerInformationDialog)
                        true
                    }
                )
            }

            // For Saved Marker
            state.mapMarker.forEach { marker ->
                Marker(
                    state = MarkerState(
                        position = LatLng(marker.lat, marker.lng)
                    ),
                    draggable = false,
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                )
                CustomMarkerInfoWindow(marker = marker, mapViewModel = mapViewModel)
            }

        }

        DeleteAlertDialog(
            showDialog = state.isDeleteDialogVisible,
            onDismiss = { mapViewModel.onEvent(MapEvent.ToggleDeleteMarkerDialog()) },
            onConfirm = {
                mapViewModel.onEvent(MapEvent.OnInfoWindowClick(state.mapMarkerSaved))
            })

        MarkerInformationDialog(showDialog = state.isMarkerInformationDialogVisible,
            context = context,
            lat = state.latLng?.latitude,
            lng = state.latLng?.longitude,
            onConfirm = { mapsMarkerValue ->
                mapViewModel.onEvent(
                    MapEvent.OnMapSave(
                        mapsMarkerValue
                    )
                )
            }, onDismiss = { mapViewModel.onEvent(MapEvent.ToggleMarkerInformationDialog) })
    }
}