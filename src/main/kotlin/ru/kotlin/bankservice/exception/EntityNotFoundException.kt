package ru.kotlin.bankservice.exception

class EntityNotFoundException(
    override val message: String
) : RuntimeException(message)
