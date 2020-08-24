package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.TransferDTO
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.entity.Operation
import java.math.BigDecimal

@Transactional
interface OperationService {

    fun create(operation: Operation): Operation
}