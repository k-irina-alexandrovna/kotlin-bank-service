package ru.kotlin.bankservice.repository

import ru.kotlin.bankservice.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>