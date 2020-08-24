package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.model.Operation
import ru.kotlin.bankservice.repository.OperationRepository
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.service.OperationService

@Service
class OperationServiceImpl(
    private val operationRepository: OperationRepository
): OperationService {

    override fun save(operation: Operation) = operationRepository.saveAndFlush(operation)
}