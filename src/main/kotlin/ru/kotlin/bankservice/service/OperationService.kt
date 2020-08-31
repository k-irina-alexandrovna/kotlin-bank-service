package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.Operation

@Transactional
interface OperationService {

    fun create(operation: Operation): Operation

    @Transactional( readOnly = true)
    fun findByAccount(account: Account): Iterable<Operation>
}