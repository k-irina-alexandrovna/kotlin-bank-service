package ru.kotlin.bankservice.controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.OperationService
import ru.kotlin.bankservice.service.converter.OperationDtoConverter

@RestController
@RequestMapping("operations")
class OperationController(
    private val operationService: OperationService,
    private val accountService: AccountService,
    private val converter: OperationDtoConverter
) {
    @ApiOperation("Получить все операции по счёту")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id : Long) = accountService.get(id)
        .let { operationService.findByAccount(it) }
        .map { converter.convert(it) }
}