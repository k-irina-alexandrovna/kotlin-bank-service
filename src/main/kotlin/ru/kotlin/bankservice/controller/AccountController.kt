package ru.kotlin.bankservice.controller

import ru.kotlin.bankservice.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.kotlin.bankservice.model.dto.AccountDTO

@RestController()
@RequestMapping("accounts")
class AccountController (
    private val accountService: AccountService
) {

    @GetMapping
    fun getAll() = accountService.getAll()

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id : Long) = accountService.get(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody accountDTO: AccountDTO) = accountService.create(accountDTO)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = accountService.delete(id)
}