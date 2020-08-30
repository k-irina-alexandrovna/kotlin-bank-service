package ru.kotlin.bankservice.service.validators

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.kotlin.bankservice.model.dto.UserDTO
import kotlin.test.assertFailsWith

internal class UserValidatorServiceTest {

    private val validator: Validator = ValidatorService()

    @Test
    fun `validate userDTO should return null for ok`() {
        // given
        val userDTO = UserDTO(
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
        val userDTO = UserDTO(
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
        val userDTO = UserDTO(
            fullName = "Иванов Иван Иванович"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(userDTO)
        }
    }
}