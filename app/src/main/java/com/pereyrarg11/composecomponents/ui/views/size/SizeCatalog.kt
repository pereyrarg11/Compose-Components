package com.pereyrarg11.composecomponents.ui.views.size

enum class SizeCatalog(val description: String) {
    SMALL("Personal"),
    MEDIUM("Mediana"),
    LARGE("Grande"),
    JUMBO("Jumbo");

    companion object {
        fun from(description: String): SizeCatalog? =
            values().firstOrNull { it.description.equals(description, true) }
    }
}