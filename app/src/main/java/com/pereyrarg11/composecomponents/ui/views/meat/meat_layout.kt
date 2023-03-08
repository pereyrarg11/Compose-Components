package com.pereyrarg11.composecomponents.ui.views.meat

import androidx.compose.runtime.Composable
import androidx.compose.ui.state.ToggleableState
import com.pereyrarg11.composecomponents.ui.views.components.checkbox.CheckboxOption
import com.pereyrarg11.composecomponents.ui.views.components.checkbox.CheckboxComponent
import com.pereyrarg11.composecomponents.ui.views.components.checkbox.TriStateCheckboxComponent
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout

@Composable
fun FoodFormGroup(options: List<CheckboxOption>) {
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

    FormGroupLayout(label = "Carnes") {
        TriStateCheckboxComponent(state = allOptionsState, onClickListener = onAllOptionsClick)
        options.forEach {
            CheckboxComponent(config = it)
        }
    }
}

fun buildFoodOptions(
    checkedOptions: List<String>,
    onOptionChecked: (String, Boolean) -> Unit
): List<CheckboxOption> {
    return MeatCatalog.values().map { foodItem ->
        CheckboxOption(
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
