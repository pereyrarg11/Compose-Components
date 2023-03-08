package com.pereyrarg11.composecomponents.ui.views.meat

enum class MeatCatalog(val description: String) {
    HAM("Jamón"),
    SALAMI("Salami"),
    SAUSAGE("Salchicha"),
    BACON("Tocino"),
    PEPPERONI("Peperoni");

    companion object {
        fun from(description: String): MeatCatalog? =
            values().firstOrNull {
                it.description.equals(description, true)
            }
    }
}