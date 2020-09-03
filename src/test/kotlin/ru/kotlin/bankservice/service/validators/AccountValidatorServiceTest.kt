package ru.kotlin.bankservice.service.validators

import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import java.math.BigDecimal
import kotlin.test.assertFailsWith

internal class AccountValidatorServiceTest {

    private val validator: Validator = ValidatorService()

    @Test
    fun `validate accountDTO should return null for ok`() {
        // given
        val accountDTO = AccountRequestDTO(
            number = 112345,
            currency = "RUB",
            userId = 1
        )
        // when
        val result = validator.validate(accountDTO)

        // then
        assertNull(result)
    }

    @Test
    fun `validate accountDTO should return error for empty number`() {
        // given
        val accountDTO = AccountRequestDTO(
            currency = "RUB",
            userId = 1
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(accountDTO)
        }
    }

    @Test
    fun `validate accountDTO should return error for empty currency`() {
        // given
        val accountDTO = AccountRequestDTO(
            number = 112345,
            balance = BigDecimal.TEN,
            userId = 1
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(accountDTO)
        }
    }

    @Test
    fun `validate accountDTO should return error for any currency`() {
        // given
        val accountDTO = AccountRequestDTO(
            number = 112345,
            currency = "XXX",
            userId = 1
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(accountDTO)
        }
    }

    @Test
    fun `validate accountDTO should return error for empty userId`() {
        // given
        val accountDTO = AccountRequestDTO(
            number = 112345,
            currency = "RUB"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(accountDTO)
        }
    }
}
