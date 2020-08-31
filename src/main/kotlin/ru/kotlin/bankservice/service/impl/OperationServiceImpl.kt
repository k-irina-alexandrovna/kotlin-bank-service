package ru.kotlin.bankservice.service.impl

import org.springframework.stereotype.Service
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.Operation
import ru.kotlin.bankservice.repository.OperationRepository
import ru.kotlin.bankservice.service.OperationService


@Service
class OperationServiceImpl(
    private val operationRepository: OperationRepository
): OperationService {

    override fun create(operation: Operation): Operation = operationRepository.save(operation)

    override fun findByAccount(account: Account): Iterable<Operation> = operationRepository.findByAccount(account)
}