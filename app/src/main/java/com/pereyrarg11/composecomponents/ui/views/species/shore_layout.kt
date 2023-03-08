package com.pereyrarg11.composecomponents.ui.views.species

import androidx.compose.runtime.Composable
import com.pereyrarg11.composecomponents.ui.views.components.ShoreRadioButton
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout

@Composable
fun ShoreFormGroup(options: List<RadioButtonOption>) {
    FormGroupLayout(label = "Orilla") {
        options.forEach { option ->
            ShoreRadioButton(optionConfig = option)
        }
    }
}

fun buildShoreOptions(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
): List<RadioButtonOption> {
    return ShoreCatalog.values().map { shoreOption ->
        RadioButtonOption(
            label = shoreOption.description,
            isSelected = shoreOption == ShoreCatalog.from(selectedOption),
            onClickListener = { onOptionSelected(shoreOption.description) }
        )
    }
}
