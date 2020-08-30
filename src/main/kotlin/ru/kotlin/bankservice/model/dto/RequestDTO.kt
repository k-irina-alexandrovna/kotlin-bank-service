package ru.kotlin.bankservice.model.dto

import java.math.BigDecimal

data class UserRequestDTO(
    val id: Long? = null,
    val fullName: String? = null,
    val passport: String? = null
)

data class AccountRequestDTO(
    val id: Long? = null,
    val number: Number? = null,
    val balance: BigDecimal = BigDecimal.ZERO,
    val currency: String? = null,
    val userId: Long? = null
)

data class TransferRequestDTO(
    val senderAccountNumber: Number? = null,
    val receiverAccountNumber: Number? = null,
    val amount: BigDecimal? = null,
    val operation: String? = null
)