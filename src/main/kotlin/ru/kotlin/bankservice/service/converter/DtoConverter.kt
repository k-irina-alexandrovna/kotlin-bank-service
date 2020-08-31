package ru.kotlin.bankservice.service.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ru.kotlin.bankservice.model.dto.AccountResponseDTO
import ru.kotlin.bankservice.model.dto.OperationResponseDTO
import ru.kotlin.bankservice.model.dto.TransferRequestDTO
import ru.kotlin.bankservice.model.dto.TransferResponseDTO
import ru.kotlin.bankservice.model.dto.UserResponseDTO
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.Operation
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.service.AccountService

@Component
class UserDtoConverter: Converter<User, UserResponseDTO> {
    override fun convert(source: User): UserResponseDTO = UserResponseDTO (
            id = source.id,
            fullName = source.fullName,
            passport = source.passport
        )
}

@Component
class AccountDtoConverter(
    private val dtoConverter: UserDtoConverter
): Converter<Account, AccountResponseDTO> {

    override fun convert(source: Account): AccountResponseDTO = AccountResponseDTO (
        id = source.id,
        number = source.number,
        balance = source.balance,
        currency = source.currency,
        user =  source.user.let { dtoConverter.convert(it) }
    )
}

@Component
class TransferDtoConverter(
    private val accountService: AccountService,
    private val dtoConverter: AccountDtoConverter
): Converter<TransferRequestDTO, TransferResponseDTO> {

    override fun convert(source: TransferRequestDTO): TransferResponseDTO = TransferResponseDTO (
        senderAccount = getAccountResponseDtoByNumber(source.senderAccountNumber!!),
        receiverAccount = getAccountResponseDtoByNumber(source.receiverAccountNumber!!)
    )

    private fun getAccountResponseDtoByNumber(number: Number) =
        accountService.getByNumber(number).let { dtoConverter.convert(it) }
}

@Component
class OperationDtoConverter: Converter<Operation, OperationResponseDTO> {

    override fun convert(source: Operation): OperationResponseDTO = OperationResponseDTO (
        createdTime = source.createdTime,
        accountNumber = source.account.number,
        amount = source.amount
    )
}