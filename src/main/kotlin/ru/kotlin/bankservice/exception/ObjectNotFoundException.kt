package ru.kotlin.bankservice.exception

class ObjectNotFoundException(
    override val message: String
) : RuntimeException(message)
