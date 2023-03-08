package com.pereyrarg11.composecomponents.ui.views.species

enum class ShoreCatalog(val description: String) {
    NORMAL("Normal"),
    CHEESE("Rellena de queso"),
    SNACKS("Con bocadillos");

    companion object {
        fun from(name: String): ShoreCatalog? =
            values().firstOrNull {
                it.description.equals(name, true)
            }
    }
}