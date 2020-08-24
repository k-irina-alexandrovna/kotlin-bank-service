package ru.kotlin.bankservice.service

import ru.kotlin.bankservice.model.Operation

interface OperationService {
    fun save(operation: Operation): Operation
}