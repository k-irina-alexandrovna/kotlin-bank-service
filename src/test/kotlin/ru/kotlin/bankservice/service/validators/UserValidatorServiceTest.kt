package ru.kotlin.bankservice.service.validators

import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import kotlin.test.assertFailsWith

internal class UserValidatorServiceTest {

    private val validator: Validator = ValidatorService()

    @Test
    fun `validate userDTO should return null for ok`() {
        // given
        val userDTO = UserRequestDTO(
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        // when
        val result = validator.validate(userDTO)

        // then
        assertNull(result)
    }

    @Test
    fun `validate userDTO should return error for empty fullName`() {
        // given
        val userDTO = UserRequestDTO(
            passport = "1234567890"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(userDTO)
        }
    }

    @Test
    fun `validate userDTO should return error for empty passport`() {
        // given
        val userDTO = UserRequestDTO(
            fullName = "Иванов Иван Иванович"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(userDTO)
        }
    }
}