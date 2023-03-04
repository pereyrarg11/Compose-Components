package com.pereyrarg11.composecomponents.ui.views.checkbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.pereyrarg11.composecomponents.ui.views.FormGroupLabel

@Composable
fun FoodFormGroup(options: List<CheckboxInfo>) {
    val allOptionsState = if (options.all { it.isChecked }) {
        ToggleableState.On
    } else if (options.all { !it.isChecked }) {
        ToggleableState.Off
    } else {
        ToggleableState.Indeterminate
    }
    val onAllOptionsClick = {
        val isOn = allOptionsState != ToggleableState.On
        options.forEach { it.onCheckedChanged(isOn) }
    }

    Column(Modifier.fillMaxWidth()) {
        FormGroupLabel(text = "Alimentación")
        Spacer(
            Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
        TriStateCheckboxOption(state = allOptionsState, onClickListener = onAllOptionsClick)
        options.forEach {
            CheckboxOption(config = it)
        }
    }
}

@Composable
fun CheckboxOption(config: CheckboxInfo) {
    Row(
        Modifier
            .toggleable(
                value = config.isChecked,
                role = Role.Checkbox,
                onValueChange = config.onCheckedChanged
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = config.isChecked,
            onCheckedChange = null,
        )
        Spacer(
            Modifier
                .padding(8.dp)
                .fillMaxHeight()
        )
        Text(text = config.label)
    }
}

@Composable
fun TriStateCheckboxOption(state: ToggleableState, onClickListener: () -> Unit) {
    Row(
        Modifier
            .triStateToggleable(
                state = state,
                role = Role.Checkbox,
                onClick = onClickListener
            )
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        TriStateCheckbox(state = state, onClick = null)
        Spacer(
            Modifier
                .padding(8.dp)
                .fillMaxHeight()
        )
        Text(text = "Todas las opciones")
    }
}

@Composable
fun createFoodOptions(labels: List<String>): List<CheckboxInfo> {
    return labels.map {
        var isChecked by rememberSaveable { mutableStateOf(false) }
        CheckboxInfo(
            label = it,
            isChecked = isChecked,
            onCheckedChanged = { newStatus -> isChecked = newStatus }
        )
    }
}
