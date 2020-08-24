package ru.kotlin.bankservice.service

import ru.kotlin.bankservice.model.User
import java.util.*

interface UserService {

    fun getAll(): Iterable<User>

    fun get(id: Long): Optional<User>

    fun create(user: User): User

    fun update(id: Long, user: User): User

    fun delete(id: Long)
}