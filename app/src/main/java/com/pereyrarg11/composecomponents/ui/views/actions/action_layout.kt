package com.pereyrarg11.composecomponents.ui.views.actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Actions(
    actionsEnabled: Boolean,
    discardEnabled: Boolean,
    onSubmitListener: () -> Unit,
    onDiscardListener: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Row {
            OutlinedButton(onClick = onDiscardListener, enabled = discardEnabled) {
                Text(text = "Descartar")
            }
            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
            )
            Button(onClick = onSubmitListener, enabled = actionsEnabled) {
                Text(text = "Guardar")
            }
        }
    }
}