package ru.kotlin.bankservice.model.dto

import ru.kotlin.bankservice.model.entity.User
import java.time.LocalDate

data class UserDTO(
    val id: Long,
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val birthDate: LocalDate,
    val passport: String
)

fun UserDTO.fromDTO() = User(
    lastName = this.lastName,
    firstName = this.firstName,
    middleName = this.middleName,
    birthDate = this.birthDate,
    passport = this.passport
)