package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.dto.AccountDTO

@Transactional
interface AccountService {

    fun getAll(): Iterable<Account>

    fun get(id: Long): Account

    fun create(accountDTO: AccountDTO): Account

    fun update(id: Long, account: Account): Account

    fun update(account: Account): Account

    fun delete(id: Long)

    fun isExists(id: Long): Boolean

    fun existsByNumber(number: Number): Boolean

    fun getByNumber(number: Number): Account
}