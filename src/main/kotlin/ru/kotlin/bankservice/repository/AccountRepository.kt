package ru.kotlin.bankservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kotlin.bankservice.model.entity.Account

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun existsByNumber(number: Number): Boolean

    fun findByNumber(number: Number): Account
}
