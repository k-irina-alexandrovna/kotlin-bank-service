package ru.kotlin.bankservice.exception

class ValidationException(
    override val message: String
) : RuntimeException(message)
