package com.pereyrarg11.composecomponents.ui.views.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiscardDialog(
    isDisplayed: Boolean,
    onDismissListener: () -> Unit,
    onConfirmListener: () -> Unit
) {
    if (isDisplayed) {
        AlertDialog(
            onDismissRequest = onDismissListener,
            title = {
                Text(text = "Descartar orden")
            },
            text = {
                Text(text = "¿Deseas descartar la información capturada?")
            },
            dismissButton = {
                TextButton(onClick = onDismissListener) {
                    Text(text = "Cancelar")
                }
            },
            confirmButton = {
                Button(onClick = onConfirmListener) {
                    Text(text = "Confirmar")
                }
            },
        )
    }
}

@Preview
@Composable
fun DiscardDialogPreview() {
    DiscardDialog(true, {}, {})
}