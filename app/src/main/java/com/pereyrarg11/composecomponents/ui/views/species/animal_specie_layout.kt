package com.pereyrarg11.composecomponents.ui.views.species

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.pereyrarg11.composecomponents.ui.views.FormGroupLabel

@Composable
fun AnimalSpecieFormGroup(options: List<AnimalSpecieOption>) {
    Column(Modifier.fillMaxWidth()) {
        FormGroupLabel(text = "Especie")
        Spacer(
            Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
        options.forEach { option ->
            AnimalSpecieRadioButton(optionConfig = option)
        }
    }
}

@Composable
fun AnimalSpecieRadioButton(optionConfig: AnimalSpecieOption) {
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

@Composable
fun buildAnimalSpecieOptions(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
): List<AnimalSpecieOption> {
    return AnimalSpecieCatalog.values().map { animalSpecie ->
        AnimalSpecieOption(
            label = animalSpecie.familyName,
            isSelected = animalSpecie == AnimalSpecieCatalog.from(selectedOption),
            onClickListener = { onOptionSelected(animalSpecie.familyName) }
        )
    }
}
