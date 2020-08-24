package ru.kotlin.bankservice.service

import ru.kotlin.bankservice.model.Account
import java.util.*

interface AccountService {

    fun getAll(): Iterable<Account>

    fun get(id: Long): Account

    fun create(account: Account): Account

    fun update(id: Long, account: Account): Account

    fun delete(id: Long)
}