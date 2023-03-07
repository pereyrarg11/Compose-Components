package com.pereyrarg11.composecomponents.ui.views.payment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout


@Composable
fun PaymentMethodFormGroup(selectedMethod: String, onPaymentMethodSelected: (String) -> Unit) {
    FormGroupLayout(label = "Método de Pago") {
        PaymentMethodDropdown(
            selectedText = selectedMethod,
            onItemSelected = onPaymentMethodSelected
        )
    }
}

@Composable
fun PaymentMethodDropdown(selectedText: String, onItemSelected: (String) -> Unit) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = onItemSelected,
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { isExpanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            PaymentMethodCatalog.values().forEach { paymentMethod ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    onItemSelected(paymentMethod.description)
                }) {
                    Text(text = paymentMethod.description)
                }
            }
        }
    }
}