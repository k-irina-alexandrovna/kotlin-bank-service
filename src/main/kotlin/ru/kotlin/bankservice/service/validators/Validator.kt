package ru.kotlin.bankservice.service.validators

import ru.kotlin.bankservice.model.dto.UserDTO
import ru.kotlin.bankservice.exception.Error
import ru.kotlin.bankservice.model.dto.AccountDTO
import ru.kotlin.bankservice.model.dto.TransferDTO
import ru.kotlin.bankservice.model.entity.Account

interface Validator {

    fun validate(user: UserDTO): Error?

    fun validate(account: AccountDTO): Error?

    fun validate(transfer: TransferDTO): Error?
}