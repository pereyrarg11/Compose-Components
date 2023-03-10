package com.pereyrarg11.composecomponents.ui.views.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pereyrarg11.composecomponents.ui.views.components.spacer.HorizontalSpacer

@Composable
fun SubmitDialog(
    isVisible: Boolean,
    data: PizzaOrder,
    onDismissListener: () -> Unit,
    onConfirmListener: () -> Unit,
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismissListener) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(text = "Enviar orden", modifier = Modifier.padding(8.dp))
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)

                AttributeRow(
                    icon = Icons.Outlined.Person,
                    label = "Cliente",
                    description = data.customerName
                )
                AttributeRow(
                    icon = Icons.Outlined.KebabDining,
                    label = "Carnes",
                    description = data.meatList.joinToString(", ")
                )
                AttributeRow(
                    icon = Icons.Outlined.Straighten,
                    label = "Tamaño",
                    description = data.size
                )
                AttributeRow(
                    icon = Icons.Outlined.Circle,
                    label = "Orilla",
                    description = data.shore
                )
                AttributeRow(
                    icon = Icons.Outlined.Payments,
                    label = "Método de pago",
                    description = data.paymentMethod
                )
                AttributeRow(
                    icon = Icons.Outlined.LocalPizza,
                    label = "Rebanadas",
                    description = data.sliceCount.toString()
                )

                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)
                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(onClick = onDismissListener) {
                        Text(text = "Cancelar")
                    }
                    TextButton(onClick = onConfirmListener) {
                        Text(text = "Confirmar")
                    }
                }
            }
        }
    }
}

@Composable
fun AttributeRow(icon: ImageVector, label: String, description: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, label)
        HorizontalSpacer(8)
        Column {
            Text(text = label, color = Color.LightGray, fontSize = 10.sp)
            Text(text = description)
        }
    }
}