package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.TransferDTO
import ru.kotlin.bankservice.model.entity.Account
import java.math.BigDecimal

@Transactional
interface TransferService {

    fun execute(transferDTO: TransferDTO)
}