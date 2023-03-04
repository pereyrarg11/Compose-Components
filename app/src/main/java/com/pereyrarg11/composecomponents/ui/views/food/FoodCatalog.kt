package com.pereyrarg11.composecomponents.ui.views.food

enum class FoodCatalog(val description: String) {
    CARNE("Carne"),
    CROQUETTES("Croquetas"),
    PRAY("Presas vivas"),
    VEGETABLES("Vegetales"),
    VITAMINS("Suplemento");

    companion object {
        fun from(description: String): FoodCatalog? =
            values().firstOrNull {
                it.description.equals(description, true)
            }
    }
}