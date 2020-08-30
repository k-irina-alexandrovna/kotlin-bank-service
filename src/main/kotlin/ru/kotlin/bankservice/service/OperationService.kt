package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.entity.Operation

@Transactional
interface OperationService {

    fun create(operation: Operation): Operation
}