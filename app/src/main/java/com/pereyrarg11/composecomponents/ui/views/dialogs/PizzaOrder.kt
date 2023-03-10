package com.pereyrarg11.composecomponents.ui.views.dialogs

data class PizzaOrder(
    val customerName: String,
    val meatList: List<String>,
    val size: String,
    val shore: String,
    val paymentMethod: String,
    val sliceCount: Int,
)
