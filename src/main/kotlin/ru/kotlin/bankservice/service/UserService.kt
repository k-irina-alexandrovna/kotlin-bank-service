package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import ru.kotlin.bankservice.model.entity.User

@Transactional
interface UserService {

    @Transactional(readOnly = true)
    fun getAll(): Iterable<User>

    @Transactional(readOnly = true)
    fun get(id: Long): User

    fun create(userRequestDTO: UserRequestDTO): User

    fun update(id: Long, userRequestDTO: UserRequestDTO): User

    fun delete(id: Long)

    @Transactional(readOnly = true)
    fun isExists(id: Long): Boolean

    @Transactional(readOnly = true)
    fun isExistsByPassport(passport: String): Boolean
}
