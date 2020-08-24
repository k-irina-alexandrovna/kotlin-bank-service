package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.model.dto.UserDTO

@Transactional
interface UserService {

    fun getAll(): Iterable<User>

    fun get(id: Long): User

    fun create(userDTO: UserDTO): User

    fun update(id: Long, userDTO: UserDTO): User

    fun delete(id: Long)

    fun isExists(id: Long): Boolean
}