package ru.kotlin.bankservice.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.kotlin.bankservice.model.dto.AccountDTO
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.model.enums.Currency
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.UserService
import java.math.BigDecimal
import java.time.LocalDate

internal class AccountServiceImplTest {

    private val accountRepository = mockk<AccountRepository>(relaxUnitFun = true)
    private val userService = mockk<UserService>(relaxUnitFun = true)

    private val accountService: AccountService = AccountServiceImpl(
        accountRepository, userService
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
        every { accountRepository.getOne(any()) } returns account

        // when
        val receivedAccount = accountService.get(account.id)

        // then
        verify { accountRepository.getOne(account.id) }
        assertEquals(account, receivedAccount)
    }

    @Test
    fun `create should create new account`() {
        // given
        val account = Account(
            number = 111111111,
            currency = Currency.RUB,
            user = user
        )
        val accountDTO = AccountDTO(
            number = 111111111,
            currency = Currency.RUB.name,
            userId = user.id
        )
        every { userService.get(any())} returns user
        every { userService.isExists(any())} returns true
        every { accountRepository.save(account) } returns account

        // when
        val newAccount = accountService.create(accountDTO)

        // then
        verify { accountRepository.save(account) }
        assertEquals(account, newAccount)
    }

    companion object{
        private val user = User(
            id = 1L,
            lastName = "Иванов",
            firstName = "Иван",
            middleName = "Иванович",
            birthDate = LocalDate.MIN,
            passport = "1234567890"
        )
    }
}