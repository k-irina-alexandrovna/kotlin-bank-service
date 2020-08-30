package ru.kotlin.bankservice.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.model.dto.TransferRequestDTO
import ru.kotlin.bankservice.model.dto.TransferResponseDTO
import ru.kotlin.bankservice.service.TransferService
import ru.kotlin.bankservice.service.converter.TransferDtoConverter

@RestController
@RequestMapping("transfer")
class TransferController(
    private val transferService : TransferService,
    private val converter: TransferDtoConverter
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody transferRequestDTO: TransferRequestDTO): TransferResponseDTO {
        transferService.transfer(transferRequestDTO)
        return converter.convert(transferRequestDTO)
    }
}