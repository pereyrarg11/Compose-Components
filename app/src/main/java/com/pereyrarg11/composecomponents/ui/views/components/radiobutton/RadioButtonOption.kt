package com.pereyrarg11.composecomponents.ui.views.components.radiobutton

data class RadioButtonOption(
    val label: String = "",
    val isSelected: Boolean = false,
    val onClickListener: () -> Unit,
)
