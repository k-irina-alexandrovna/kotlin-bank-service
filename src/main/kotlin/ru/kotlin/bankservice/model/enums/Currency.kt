package ru.kotlin.bankservice.model.enums

enum class Currency(val description: String) {
    USD("Dollar"),
    EUR("Euro"),
    RUB("Ruble");
}

fun isValid(value: String): Boolean {
    try {
        Currency.valueOf(value)
        return true
    } catch (e: Exception) {
        return false
    }
}