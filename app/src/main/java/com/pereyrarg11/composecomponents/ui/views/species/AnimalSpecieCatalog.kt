package com.pereyrarg11.composecomponents.ui.views.species

enum class AnimalSpecieCatalog(val familyName: String) {
    DOG("Perro"),
    CAT("Gato"),
    BIRD("Ave"),
    REPTILE("Reptil"),
    OTHER("Otro");

    companion object {
        fun from(name: String): AnimalSpecieCatalog? =
            values().firstOrNull {
                it.familyName.equals(name, true)
            }
    }
}