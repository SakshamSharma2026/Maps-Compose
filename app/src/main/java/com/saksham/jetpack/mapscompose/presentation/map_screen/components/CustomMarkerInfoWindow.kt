package com.saksham.jetpack.mapscompose.presentation.map_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.saksham.jetpack.mapscompose.R
import com.saksham.jetpack.mapscompose.domain.model.MapMarker
import com.saksham.jetpack.mapscompose.domain.util.MapEvent
import com.saksham.jetpack.mapscompose.presentation.map_screen.MapViewModel

@Composable
fun CustomMarkerInfoWindow(marker: MapMarker, mapViewModel: MapViewModel) {
    MarkerInfoWindow(
        state = MarkerState(
            position = LatLng(marker.lat, marker.lng)
        ),
        onInfoWindowClick = {
            mapViewModel.onEvent(
                MapEvent.ToggleDeleteMarkerDialog(
                    marker
                )
            )
        }
    ) {
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(8.dp),
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                if (!marker.name.isNullOrEmpty()) {
                    Text(
                        text = "${stringResource(id = R.string.name)} : ${marker.name}",
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                if (!marker.age.isNullOrEmpty()) {
                    Text(
                        text = "${stringResource(id = R.string.age)} : ${marker.age}",
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                if (!marker.relation.isNullOrEmpty()) {
                    Text(
                        text = "${stringResource(id = R.string.relation)} : ${marker.relation}",
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                Text(
                    text = "${stringResource(id = R.string.address)} : ${marker.address}",
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${stringResource(id = R.string.lat)} : ${marker.lat}",
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${stringResource(id = R.string.lng)} : ${marker.lng}",
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.click_to_delete),
                    style = TextStyle(fontSize = 10.sp),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}