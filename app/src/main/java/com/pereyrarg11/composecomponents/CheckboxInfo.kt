package com.pereyrarg11.composecomponents

data class CheckboxInfo(
    val label: String = "",
    var isChecked: Boolean = false,
    var onCheckedChanged: (Boolean) -> Unit = {},
)
