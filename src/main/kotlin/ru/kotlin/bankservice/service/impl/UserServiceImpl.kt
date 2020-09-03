package ru.kotlin.bankservice.service.impl

import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.ObjectAlreadyExists
import ru.kotlin.bankservice.exception.ObjectNotFoundException
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.repository.UserRepository
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.validators.Validator

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val validator: Validator
) : UserService {

    override fun getAll(): Iterable<User> {
        return userRepository.findAll()
    }

    override fun get(id: Long): User = userRepository.findById(id)
        .let {
            if (it.isPresent) {
                it.get()
            } else {
                throw ObjectNotFoundException("Клиент с id $id не найден")
            }
        }

    override fun create(userRequestDTO: UserRequestDTO): User {
        validator.validate(userRequestDTO)
        checkIsPassportExists(userRequestDTO.passport!!)
        User(
            fullName = userRequestDTO.fullName!!,
            passport = userRequestDTO.passport
        ).let {
            return userRepository.save(it)
        }
    }

    override fun update(id: Long, userRequestDTO: UserRequestDTO): User {
        validator.validate(userRequestDTO)
        if (!isExists(id)) {
            throw ObjectNotFoundException("Клиент с id $id не найден")
        }
        checkIsPassportExists(userRequestDTO.passport!!)
        User(
            fullName = userRequestDTO.fullName!!,
            passport = userRequestDTO.passport
        ).let {
            return userRepository.save(it)
        }
    }

    override fun delete(id: Long) = userRepository.deleteById(id)

    override fun isExists(id: Long) = userRepository.existsById(id)

    override fun isExistsByPassport(passport: String) = userRepository.existsByPassport(passport)

    private fun checkIsPassportExists(passport: String): Exception? {
        if (isExistsByPassport(passport)) {
            throw ObjectAlreadyExists("Клиент с паспортом $passport уже зарегистрирован")
        }
        return null
    }
}
