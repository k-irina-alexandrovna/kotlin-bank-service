package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository
): AccountService
{

    override fun getAll(): Iterable<Account> = accountRepository.findAll()

    override fun get(id: Long) = accountRepository.getOne(id)

    override fun create(account: Account) = accountRepository.save(account);

    override fun update(id: Long, account: Account) = accountRepository.save(account.copy(id = id))

    override fun delete(id: Long) = accountRepository.deleteById(id);
}