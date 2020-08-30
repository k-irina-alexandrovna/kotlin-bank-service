package ru.kotlin.bankservice.service

import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.model.dto.TransferRequestDTO

@Transactional
interface TransferService {

    fun transfer(transferRequestDTO: TransferRequestDTO)
}