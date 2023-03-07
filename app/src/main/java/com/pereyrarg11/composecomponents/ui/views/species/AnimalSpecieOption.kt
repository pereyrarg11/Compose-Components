package com.pereyrarg11.composecomponents.ui.views.species

data class AnimalSpecieOption(
    val label: String = "",
    var isSelected: Boolean = false,
    val onClickListener: () -> Unit,
)
