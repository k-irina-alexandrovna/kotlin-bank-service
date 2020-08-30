package ru.kotlin.bankservice.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.kotlin.bankservice.model.dto.UserDTO
import ru.kotlin.bankservice.model.dto.fromDTOtoEntity
import ru.kotlin.bankservice.model.entity.User
import ru.kotlin.bankservice.repository.UserRepository
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.validators.Validator
import java.util.*

internal class UserServiceImplTest {

    private val userRepository = mockk<UserRepository>(relaxUnitFun = true)
    private val validator = mockk<Validator>(relaxUnitFun = true)

    private val userService: UserService = UserServiceImpl(
        userRepository, validator
    )

    @Test
    fun `getAll should return all users`() {
        // given
        val user1 = User(
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        val user2 = User(
            fullName = "Петров Пётр Петрович",
            passport = "0987654321"
        )
        every { userRepository.findAll() } returns listOf(user1, user2)

        // when
        val allUsers = userService.getAll()

        // then
        verify { userRepository.findAll() }
        Assertions.assertEquals(2, allUsers.count())
        Assertions.assertTrue(allUsers.toList().containsAll(listOf(user1, user2)))
    }

    @Test
    fun `get should returt user by id`() {
        val user = User(
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        every { userRepository.findById(any()) } returns Optional.of(user)

        // when
        val receivedUser = userService.get(user.id)

        // then
        verify { userRepository.findById(user.id) }
        Assertions.assertEquals(user, receivedUser)
    }

    @Test
    fun `create should create new user`() {
        // given
        val userDTO = UserDTO(
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        val user = User(
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        every { userRepository.existsByPassport(userDTO.passport!!) } returns false
        every { validator.validate(userDTO) } returns null
        every { userRepository.save(user) } returns user

        // when
        val createdUser = userService.create(userDTO)

        // then
        verify { validator.validate(userDTO) }
        verify { userRepository.save(user) }
        Assertions.assertEquals(user, createdUser)
    }

    @Test
    fun `update should update exists user`() {
        // given
        val userDTO = UserDTO(
            id = 1L,
            fullName = "Иванов Иван Иванович",
            passport = "1234567890"
        )
        val user = userDTO.fromDTOtoEntity()

        every { validator.validate(userDTO) } returns null
        every { userRepository.existsById(userDTO.id!!) } returns true
        every { userRepository.existsByPassport(userDTO.passport!!) } returns false
        every { userRepository.save(user) } returns user

        // when
        val updatedUser = userService.update(userDTO.id!!, userDTO)

        // then
        verify { userRepository.save(user) }
        verify { validator.validate(userDTO) }
        Assertions.assertEquals(user, updatedUser)
    }
}