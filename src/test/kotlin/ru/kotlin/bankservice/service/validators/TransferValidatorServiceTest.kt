package ru.kotlin.bankservice.service.validators

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.kotlin.bankservice.model.dto.TransferDTO
import java.math.BigDecimal
import kotlin.test.assertFailsWith

internal class TransferValidatorServiceTest {

    private val validator: Validator = ValidatorService()

    @Test
    fun `validate transferDTO should return null for ok`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            receiverAccountNumber = 67890,
            amount = BigDecimal.TEN,
            operation = "WITHDRAWAL"
        )
        // when
        val result = validator.validate(transferDTO)

        // then
        assertNull(result)
    }

    @Test
    fun `validate transferDTO should return error for empty senderAccountNumber`() {
        // given
        val transferDTO = TransferDTO(
            receiverAccountNumber = 67890,
            amount = BigDecimal.TEN,
            operation = "WITHDRAWAL"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }

    @Test
    fun `validate transferDTO should return error for empty receiverAccountNumber`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            amount = BigDecimal.TEN,
            operation = "WITHDRAWAL"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }

    @Test
    fun `validate transferDTO should return error for empty operation`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            receiverAccountNumber = 67890,
            amount = BigDecimal.TEN
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }

    @Test
    fun `validate transferDTO should return error for any operation`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            receiverAccountNumber = 67890,
            amount = BigDecimal.TEN,
            operation = "XXX"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }

    @Test
    fun `validate transferDTO should return error for ZERO amount`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            receiverAccountNumber = 67890,
            amount = BigDecimal.ZERO,
            operation = "WITHDRAWAL"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }

    @Test
    fun `validate transferDTO should return error for empty amount`() {
        // given
        val transferDTO = TransferDTO(
            senderAccountNumber = 12345,
            receiverAccountNumber = 67890,
            operation = "WITHDRAWAL"
        )
        // when - then
        assertFailsWith(Exception::class) {
            validator.validate(transferDTO)
        }
    }
}