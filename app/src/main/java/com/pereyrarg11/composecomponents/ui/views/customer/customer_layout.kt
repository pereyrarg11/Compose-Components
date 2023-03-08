package com.pereyrarg11.composecomponents.ui.views.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout

@Composable
fun CustomerFormGroup(name: String, onNameChange: (String) -> Unit) {
    FormGroupLayout(label = "Cliente") {
        TextInputForm(value = name, label = "Nombre", onValueChange = onNameChange)
    }
}

@Composable
fun TextInputForm(value: String, label: String, onValueChange: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
    }
}