package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import ru.kotlin.bankservice.model.entity.User

@Transactional
interface UserService {

    fun getAll(): Iterable<User>

    fun get(id: Long): User

    fun create(userRequestDTO: UserRequestDTO): User

    fun update(id: Long, userRequestDTO: UserRequestDTO): User

    fun delete(id: Long)

    fun isExists(id: Long): Boolean

    fun isExistsByPassport(passport: String): Boolean
}