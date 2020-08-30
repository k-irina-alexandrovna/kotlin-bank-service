package ru.kotlin.bankservice.model.dto

import java.math.BigDecimal

data class TransferDTO(
    val senderAccountNumber: Number? = null,
    val receiverAccountNumber: Number? = null,
    val amount: BigDecimal? = null,
    val operation: String? = null
)
