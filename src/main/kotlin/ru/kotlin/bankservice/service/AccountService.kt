package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.dto.AccountDTO

@Transactional
interface AccountService {

    fun getAll(): Iterable<Account>

    fun find(id: Long): Account

    fun create(account: AccountDTO): Account

    fun update(id: Long, account: AccountDTO): Account

    fun update(account: Account): Account

    fun delete(id: Long)

    fun findByNumber(number: Number): Account
}