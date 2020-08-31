package ru.kotlin.bankservice.controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.model.dto.UserRequestDTO
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.converter.UserDtoConverter

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService,
    private val dtoConverter: UserDtoConverter
) {
    @ApiOperation("Получить список клиентов")
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    fun getAll() =
        userService.getAll().map { dtoConverter.convert(it) }

    @ApiOperation("Получить данные клиента")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id : Long) = userService.get(id)
        .let { dtoConverter.convert(it) }

    @ApiOperation("Создать клиента")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userRequest: UserRequestDTO) = userService.create(userRequest)
        .let { dtoConverter.convert(it) }

    @ApiOperation("Обновить данные клиента")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: Long,
        @RequestBody userRequest: UserRequestDTO
    ) = userService.update(id, userRequest)
        .let { dtoConverter.convert(it) }

    @ApiOperation("Удалить клиента")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Long) = userService.delete(id)
}