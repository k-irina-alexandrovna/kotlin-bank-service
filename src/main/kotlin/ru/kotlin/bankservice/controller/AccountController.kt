package ru.kotlin.bankservice.controller

import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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
    fun create(@RequestBody account: Account) = accountService.create(account)

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: Long,
        @RequestBody account: Account
    ) = accountService.update(id, account)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = accountService.delete(id)
}