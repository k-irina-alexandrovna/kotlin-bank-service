package ru.kotlin.bankservice.exception

class TransferException(
    override val message: String
) : RuntimeException(message)
