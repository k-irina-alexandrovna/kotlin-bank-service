package ru.kotlin.bankservice.controller

import ru.kotlin.bankservice.service.TransferService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.model.dto.TransferDTO

@RestController("/payment")
class TransferController(
    private val transferService : TransferService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody transferDTO: TransferDTO) = transferService.transfer(transferDTO)
}