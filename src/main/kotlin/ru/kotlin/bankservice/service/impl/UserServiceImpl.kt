package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.User
import ru.kotlin.bankservice.repository.UserRepository
import ru.kotlin.bankservice.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService
{

    override fun getAll() : Iterable<User> {
        return userRepository.findAll();
    }

    override fun get(id: Long): Optional<User> {
        return userRepository.findById(id);
    }

    override fun create(user: User): User {
        return userRepository.save(user);
    }

    override fun update(id: Long, user: User): User {
        return userRepository.save(user.copy(id = id))
    }

    override fun delete(id: Long) {
        return userRepository.deleteById(id);
    }

}