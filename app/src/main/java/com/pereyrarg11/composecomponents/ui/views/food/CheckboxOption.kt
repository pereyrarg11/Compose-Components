package com.pereyrarg11.composecomponents.ui.views.food

data class CheckboxOption(
    val label: String = "",
    val isChecked: Boolean = false,
    val onCheckedChange: (Boolean) -> Unit = {},
)
