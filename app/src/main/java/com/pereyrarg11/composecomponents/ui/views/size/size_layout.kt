package com.pereyrarg11.composecomponents.ui.views.size

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout

@Composable
fun SizeFormGroup(
    sizeSelected: String,
    onClickSize: () -> Unit
) {
    FormGroupLayout(label = "Tamaño") {
        OutlinedTextField(value = sizeSelected,
            onValueChange = {},
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { onClickSize() }
                .fillMaxWidth()
        )
    }
}
