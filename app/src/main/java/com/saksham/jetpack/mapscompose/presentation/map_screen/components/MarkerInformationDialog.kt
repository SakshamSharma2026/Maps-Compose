package com.saksham.jetpack.mapscompose.presentation.map_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.saksham.jetpack.mapscompose.R
import com.saksham.jetpack.mapscompose.domain.model.MapMarker
import com.saksham.jetpack.mapscompose.util.getAddressFromLatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkerInformationDialog(
    context: Context,
    showDialog: Boolean,
    lat: Double?,
    lng: Double?,
    onDismiss: () -> Unit,
    onConfirm: (MapMarker) -> Unit,
) {

    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var relation by rememberSaveable { mutableStateOf("") }

    val address = getAddressFromLatLng(
        context,
        lat,
        lng
    )


    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            AlertDialog(
                modifier = Modifier.background(color = Color.White),
                onDismissRequest = onDismiss,
                content = {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.personal_details))
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(stringResource(id = R.string.name)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text(stringResource(id = R.string.age)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = relation,
                            onValueChange = { relation = it },
                            label = { Text(stringResource(id = R.string.relation)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = "$lat, $lng",
                            onValueChange = { },
                            label = {
                                Text(
                                    "${stringResource(id = R.string.lat)}, ${
                                        stringResource(
                                            id = R.string.lng
                                        )
                                    }"
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = address,
                            onValueChange = { },
                            label = { Text(stringResource(id = R.string.address)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                onConfirm(
                                    MapMarker(
                                        lat!!, lng!!, name,
                                        age,
                                        relation,
                                        address,
                                    )
                                )
                                onDismiss()
                                name = ""
                                age = ""
                                relation = ""
                            }
                        ) {
                            Text(stringResource(id = R.string.save))
                        }
                    }

                },
            )
        }
    }
}
