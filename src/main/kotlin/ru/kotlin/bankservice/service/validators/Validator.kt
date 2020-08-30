package ru.kotlin.bankservice.service.validators

import ru.kotlin.bankservice.exception.Error
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.model.dto.TransferRequestDTO
import ru.kotlin.bankservice.model.dto.UserRequestDTO

interface Validator {

    fun validate(userRequest: UserRequestDTO): Error?

    fun validate(accountRequest: AccountRequestDTO): Error?

    fun validate(transferRequest: TransferRequestDTO): Error?
}