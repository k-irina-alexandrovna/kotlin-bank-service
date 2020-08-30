package ru.kotlin.bankservice.model.dto

import java.math.BigDecimal

data class AccountDTO(
    val id: Long? = null,
    val number: Number? = null,
    val balance: BigDecimal = BigDecimal.ZERO,
    val currency: String? = null,
    val userId: Long? = null
)
