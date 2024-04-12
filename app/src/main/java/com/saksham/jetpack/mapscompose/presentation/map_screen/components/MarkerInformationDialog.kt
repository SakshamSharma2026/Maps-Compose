package com.saksham.jetpack.mapscompose.presentation.map_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
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
        AlertDialog(
            modifier = Modifier
                .background(color = Color.White),
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            onDismissRequest = onDismiss,
            content = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.personal_details),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomHintTextField(
                        text = name,
                        label = stringResource(id = R.string.name),
                        onValueChange = {
                            name = it
                        },
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomHintTextField(
                        text = age,
                        label = stringResource(id = R.string.age),
                        keyboardType = KeyboardType.Number,
                        onValueChange = {
                            age = it
                        },
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomHintTextField(
                        text = relation,
                        label = stringResource(id = R.string.relation),
                        onValueChange = {
                            relation = it
                        },
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomHintTextField(
                        text = "$lat, $lng",
                        label = "${stringResource(id = R.string.lat)}, ${
                            stringResource(
                                id = R.string.lng
                            )
                        }",
                        onValueChange = {

                        },
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomHintTextField(
                        text = address,
                        label = stringResource(id = R.string.address),
                        onValueChange = {

                        },
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                onDismiss()
                                name = ""
                                age = ""
                                relation = ""
                            }
                        ) {
                            Text(
                                stringResource(id = R.string.cancel),
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                            )
                        }
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
                            Text(
                                stringResource(id = R.string.save)
                            )
                        }
                    }
                }

            },
        )
    }
}
