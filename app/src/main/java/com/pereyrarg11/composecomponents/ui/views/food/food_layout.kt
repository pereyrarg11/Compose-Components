package com.pereyrarg11.composecomponents.ui.views.food

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.pereyrarg11.composecomponents.ui.views.FormGroupLabel

@Composable
fun FoodFormGroup(options: List<FoodOption>) {
    val allOptionsState = if (options.all { it.isChecked }) {
        ToggleableState.On
    } else if (options.all { !it.isChecked }) {
        ToggleableState.Off
    } else {
        ToggleableState.Indeterminate
    }
    val onAllOptionsClick = {
        val isOn = allOptionsState != ToggleableState.On
        options.forEach { it.onCheckedChange(isOn) }
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
fun CheckboxOption(config: FoodOption) {
    Row(
        Modifier
            .toggleable(
                value = config.isChecked,
                role = Role.Checkbox,
                onValueChange = config.onCheckedChange
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
                state = state, role = Role.Checkbox, onClick = onClickListener
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

fun buildFoodOptions(
    checkedOptions: List<String>,
    onOptionChecked: (String, Boolean) -> Unit
): List<FoodOption> {
    return FoodCatalog.values().map { foodItem ->
        FoodOption(
            label = foodItem.description,
            isChecked = checkedOptions.contains(foodItem.description),
            onCheckedChange = { newCheckedState ->
                onOptionChecked(
                    foodItem.description,
                    newCheckedState
                )
            }
        )
    }
}