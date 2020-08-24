package ru.kotlin.bankservice.repository

import ru.kotlin.bankservice.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>