package ru.kotlin.bankservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransferDTO(
    val senderAccountNumber: Number,
    val receiverAccountNumber: Number,
    val amount: BigDecimal,
    val operation: String
)
