package ru.kotlin.bankservice.model.dto

import ru.kotlin.bankservice.model.enums.Currency
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserResponseDTO(
    val id: Long,
    val fullName: String,
    val passport: String
)

data class AccountResponseDTO(
    val id: Long,
    val number: Number,
    val balance: BigDecimal,
    val currency: Currency,
    val user: UserResponseDTO
)

data class TransferResponseDTO(
    val senderAccount: AccountResponseDTO,
    val receiverAccount: AccountResponseDTO
)

data class OperationResponseDTO(
    val createdTime: LocalDateTime,
    val accountNumber: Number,
    val amount: BigDecimal
)
