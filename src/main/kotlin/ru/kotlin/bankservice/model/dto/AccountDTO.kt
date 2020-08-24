package ru.kotlin.bankservice.model.dto

import java.math.BigDecimal

data class AccountDTO(
    val number: Number,
    val balance: BigDecimal = BigDecimal.ZERO,
    val currency: String,
    val userId: Long
)
