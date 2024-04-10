package com.saksham.jetpack.mapscompose.presentation.map_screen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.saksham.jetpack.mapscompose.R

@Composable
fun DeleteAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = {
                    Text(text = stringResource(id = R.string.delete_marker))
                },
                text = {
                    Text(text = stringResource(id = R.string.delete_warning))
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onConfirm()
                            onDismiss()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.delete))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { onDismiss() }
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                }
            )
        }
    }
}