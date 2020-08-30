package ru.kotlin.bankservice.exception

import ru.kotlin.bankservice.model.enums.BankOperation
import ru.kotlin.bankservice.model.enums.Currency


class ValidationException(
    error: Error,
    val data: Any? = null
): RuntimeException(
    listOfNotNull(error.name, error.message, data).joinToString(" ")
)


enum class Error(
    val message: String
) {
    MONDATORY_FIELD_IS_EMPTY("Заполните обязательные поля"),
    AMOUNT_SUM_ERROR("Сумма операции должна быть больше 0."),
    CURRENCY_NOT_EQUALS_FOR_TRANSFER("Операции возможны только между счетами одной валюты."),
    BALANCE_IS_INSUFFICIENT("Баланс счета недостаточен. Операция отклонена."),

    CURRENCY_ERROR("Доступные валюты: ${Currency.values().asList()}"),
    BANK_OPERATION_ERROR("Доступные операции: ${BankOperation.values().asList()}"),
}