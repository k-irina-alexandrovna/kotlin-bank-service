package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.repository.UserRepository
import ru.kotlin.bankservice.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.exception.EntityNotFoundException
import ru.kotlin.bankservice.exception.ValidationException
import ru.kotlin.bankservice.model.dto.UserDTO
import ru.kotlin.bankservice.model.dto.fromDTO
import java.time.LocalDate

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService
{
    @Transactional(readOnly = true)
    override fun getAll() : Iterable<User> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun get(id: Long): User {
        return userRepository.getOne(id)
    }

    override fun create(userDTO: UserDTO): User {
        validate(userDTO)
        return userDTO.fromDTO().let { userRepository.save(it)  }
    }

    override fun update(id: Long, userDTO: UserDTO): User {
        if(!isExists(id)){
            throw EntityNotFoundException("User $id not found")
        }
        validate(userDTO)
        return userDTO.fromDTO().let { userRepository.save(it) }
    }

    override fun delete(id: Long) = userRepository.deleteById(id)

    @Transactional(readOnly = true)
    override fun isExists(id: Long) = userRepository.existsById(id)

    private fun validate(userDTO: UserDTO)= (userDTO.firstName.isNotBlank()
            && userDTO.lastName.isNotBlank()
            && userDTO.passport.isNotBlank()
            && userDTO.passport.length == 10
            && userDTO.birthDate > LocalDate.now().minusYears(18))
        .let { isValid ->
            if(!isValid) {
                throw ValidationException("Failed to validate user $userDTO")
            }
        }
}