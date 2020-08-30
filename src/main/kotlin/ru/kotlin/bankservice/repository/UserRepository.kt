package ru.kotlin.bankservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kotlin.bankservice.model.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByPassport(passport: String): Boolean
}