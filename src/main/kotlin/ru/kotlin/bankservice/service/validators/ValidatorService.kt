package ru.kotlin.bankservice.service.validators

import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.Error
import ru.kotlin.bankservice.exception.ValidationException
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.model.dto.TransferRequestDTO
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import ru.kotlin.bankservice.model.enums.BankOperation
import ru.kotlin.bankservice.model.enums.Currency
import java.math.BigDecimal

@Service
class ValidatorService : Validator {

    override fun validate(userRequest: UserRequestDTO): Error? {
        with(userRequest) {
            if (fullName.isNullOrBlank()) {
                throw ValidationException(Error.MONDATORY_FIELD_IS_EMPTY, "Поле 'Имя' не может быть пустым")
            }
            if (passport.isNullOrBlank() || !passport.matches(Regex("[0-9]{10}"))) {
                throw ValidationException(Error.MONDATORY_FIELD_IS_EMPTY, "Поле 'Паспорт' должно содержать 10 цифр")
            }
            return null
        }
    }

    override fun validate(accountRequest: AccountRequestDTO): Error? {
        with(accountRequest) {
            if (number == null) {
                throw ValidationException(
                    Error.MONDATORY_FIELD_IS_EMPTY,
                    "Поле 'Номер счёта' должно содержать числовое значение"
                )
            }
            if (currency.isNullOrBlank() || !currencyExists(currency)) {
                throw ValidationException(Error.CURRENCY_ERROR)
            }
            if (userId == null) {
                throw ValidationException(
                    Error.MONDATORY_FIELD_IS_EMPTY,
                    "Поле 'Клиент' должно содержать id владельца счёта"
                )
            }
            return null
        }
    }

    override fun validate(transferRequest: TransferRequestDTO): Error? {
        with(transferRequest) {
            if (senderAccountNumber == null) {
                throw ValidationException(
                    Error.MONDATORY_FIELD_IS_EMPTY,
                    "Поле 'Номер счёта отправителя' должно содержать числовое значение"
                )
            }
            if (receiverAccountNumber == null) {
                throw ValidationException(
                    Error.MONDATORY_FIELD_IS_EMPTY,
                    "Поле 'Номер счёта получателя' должно содержать числовое значение"
                )
            }
            if (operation.isNullOrBlank() || !bankOperationExists(operation)) {
                throw ValidationException(Error.BANK_OPERATION_ERROR)
            }
            if (amount == null || amount <= BigDecimal.ZERO) {
                throw ValidationException(Error.AMOUNT_SUM_ERROR)
            }
            return null
        }
    }

    private fun currencyExists(currency: String) = try {
        Currency.valueOf(currency)
        true
    } catch (e: java.lang.Exception) {
        false
    }

    private fun bankOperationExists(operation: String) = try {
        BankOperation.valueOf(operation)
        true
    } catch (e: java.lang.Exception) {
        false
    }
}
