package ru.kotlin.bankservice.exception

class ObjectAlreadyExists(
    override val message: String
) : RuntimeException(message)