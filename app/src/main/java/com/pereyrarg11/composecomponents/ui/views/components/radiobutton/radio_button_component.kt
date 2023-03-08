package com.pereyrarg11.composecomponents.ui.views.components.radiobutton

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun ShoreRadioButton(optionConfig: RadioButtonOption) {
    Row(
        Modifier
            .selectable(
                selected = optionConfig.isSelected,
                role = Role.RadioButton,
                onClick = optionConfig.onClickListener
            )
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        RadioButton(selected = optionConfig.isSelected, onClick = null)
        Spacer(
            Modifier
                .padding(8.dp)
                .fillMaxHeight()
        )
        Text(text = optionConfig.label)
    }
}