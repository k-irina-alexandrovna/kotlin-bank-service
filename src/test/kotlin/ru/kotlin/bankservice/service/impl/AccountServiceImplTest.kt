package ru.kotlin.bankservice.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.model.enums.Currency
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.validators.Validator
import java.util.Optional

class AccountServiceImplTest {

    private val accountRepository = mockk<AccountRepository>(relaxUnitFun = true)
    private val userService = mockk<UserService>(relaxUnitFun = true)
    private val validator = mockk<Validator>(relaxUnitFun = true)

    private val accountService: AccountService = AccountServiceImpl(
        accountRepository,
        userService,
        validator
    )

    @Test
    fun `getAll should return all accounts`() {
        // given
        val account1 = Account(
            number = 111111111,
            currency = Currency.RUB,
            user = user
        )
        val account2 = Account(
            number = 222222222,
            currency = Currency.RUB,
            user = user
        )
        every { accountRepository.findAll() } returns listOf(account1, account2)

        // when
        val allAccounts = accountService.getAll()

        // then
        verify { accountRepository.findAll() }
        assertEquals(2, allAccounts.count())
        assertTrue(allAccounts.toList().containsAll(listOf(account1, account2)))
    }

    @Test
    fun `get should return account`() {
        // given
        val account = Account(
            number = 111111111,
            currency = Currency.RUB,
            user = user
        )
        every { accountRepository.findById(any()) } returns Optional.of(account)

        // when
        val receivedAccount = accountService.get(account.id)

        // then
        verify { accountRepository.findById(account.id) }
        assertEquals(account, receivedAccount)
    }

    @Test
    fun `create should create new account`() {
        // given
        val accountDTO = AccountRequestDTO(
            number = 111111111,
            currency = Currency.RUB.name,
            userId = user.id
        )
        val account = Account(
            number = 111111111,
            currency = Currency.RUB,
            user = user
        )
        every { userService.isExists(any()) } returns true
        every { accountRepository.existsByNumber(accountDTO.number!!) } returns false
        every { validator.validate(accountDTO) } returns null
        every { userService.get(any()) } returns user
        every { accountRepository.save(account) } returns account

        // when
        val newAccount = accountService.create(accountDTO)

        // then
        verify { validator.validate(accountDTO) }
        verify { accountRepository.save(account) }
        assertEquals(account, newAccount)
    }

    companion object {
        private val user = User(
            id = 1L,
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
    }
}
