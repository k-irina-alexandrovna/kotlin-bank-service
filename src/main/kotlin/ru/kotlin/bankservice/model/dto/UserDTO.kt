package ru.kotlin.bankservice.model.dto

import ru.kotlin.bankservice.model.entity.User
import java.time.LocalDate

data class UserDTO(
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val passport: String
)

fun UserDTO.fromDTO() = User(
    lastName = this.lastName,
    firstName = this.firstName,
    middleName = this.middleName,
    passport = this.passport
)