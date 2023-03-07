package com.pereyrarg11.composecomponents.ui.views.payment

enum class PaymentMethodCatalog(val description: String) {
    CASH("Efectivo"),
    DEBIT_CARD("Tarjeta de débito"),
    CREDIT_CARD("Tarjeta de crédito"),
    COUPONS("Cupón");

    companion object {
        fun from(description: String): PaymentMethodCatalog? =
            values().firstOrNull { it.description.equals(description, true) }
    }
}