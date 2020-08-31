package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.model.entity.Account

@Transactional
interface AccountService {

    @Transactional( readOnly = true)
    fun getAll(): Iterable<Account>

    @Transactional( readOnly = true)
    fun get(id: Long): Account

    fun create(accountRequest: AccountRequestDTO): Account

    fun update(id: Long, accountRequest: AccountRequestDTO): Account

    fun update(account: Account): Account

    fun delete(id: Long)

    @Transactional( readOnly = true)
    fun getByNumber(number: Number): Account
}