package com.pereyrarg11.composecomponents.ui.views.checkbox

data class CheckboxInfo(
    val label: String = "",
    var isChecked: Boolean = false,
    var onCheckedChanged: (Boolean) -> Unit = {},
)
