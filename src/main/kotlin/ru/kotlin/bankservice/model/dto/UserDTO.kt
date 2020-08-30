package ru.kotlin.bankservice.model.dto

import ru.kotlin.bankservice.model.entity.User
import java.time.LocalDate

data class UserDTO(
    val id: Long? = null,
    val fullName: String? = null,
    val passport: String? = null
)

fun UserDTO.fromDTOtoEntity() = User(
    fullName = this.fullName!!,
    passport = this.passport!!
)