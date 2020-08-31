package ru.kotlin.bankservice.controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.converter.AccountDtoConverter

@RestController()
@RequestMapping("accounts")
class AccountController (
    private val accountService: AccountService,
    private val dtoConverter: AccountDtoConverter
) {

    @ApiOperation("Получить список счетов")
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    fun getAll() = accountService.getAll()
        .map { dtoConverter.convert(it) }

    @ApiOperation("Получить данные счёта")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id : Long) = accountService.get(id)
        .let { dtoConverter.convert(it) }

    @ApiOperation("Создать счёт")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody accountRequestDTO: AccountRequestDTO) =
        accountService.create(accountRequestDTO).let { dtoConverter.convert(it) }

    @ApiOperation("Удалить счёт")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Long) = accountService.delete(id)
}