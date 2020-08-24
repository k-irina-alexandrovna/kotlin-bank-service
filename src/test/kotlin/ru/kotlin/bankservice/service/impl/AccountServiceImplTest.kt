package ru.kotlin.bankservice.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.model.User
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import java.math.BigDecimal
import java.time.LocalDate

internal class AccountServiceImplTest {

    private val accountRepository = mockk<AccountRepository>(relaxUnitFun = true)
    private val accountService: AccountService = AccountServiceImpl(accountRepository)


    @Test
    fun `getAll should return all accounts`() {
        // given
        val account1 = Account(
            id = 1L,
            number = 111111111,
            user = user
        )
        val account2 = Account(
            id = 1L,
            number = 222222222,
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
            id = 1L,
            number = 111111111,
            user = user
        )
        every { accountRepository.getOne(any()) } returns account

        // when
        val receivedAccount = accountService.get(account.id!!)

        // then
        verify { accountRepository.getOne(account.id!!) }
        assertEquals(account, receivedAccount)
    }

    @Test
    fun `create should create new account`() {
        // given
        val account = Account(
            id = 1L,
            number = 111111111,
            user = user
        )
        every { accountRepository.save(account) } returns account

        // when
        val newAccount = accountService.create(account)

        // then
        verify { accountRepository.save(account) }
        assertEquals(account, newAccount)
    }

    @Test
    fun `update should update account`() {
        // given
        val account = Account(
            id = 1L,
            number = 111111111,
            user = user
        )
        val newAccount = account.copy(number = 3333333)
        every { accountRepository.save(newAccount) } returns newAccount

        // when
        val updatedAccount = accountService.update(account.id!!, newAccount)

        // then
        verify { accountRepository.save(newAccount) }
        assertEquals(newAccount.number, updatedAccount.number)
    }


    companion object{
        private val user = User(
            id = 1L,
            lastName = "Иванов",
            firstName = "Иван",
            middleName = "Иванович",
            birthday = LocalDate.MIN,
            passport = "1234567890"
        )
    }
}